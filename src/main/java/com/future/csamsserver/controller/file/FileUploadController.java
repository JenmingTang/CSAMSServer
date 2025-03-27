package com.future.csamsserver.controller.file;

import cn.dev33.satoken.util.SaResult;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.future.csamsserver.domain.LocalFile;
import com.future.csamsserver.enumeration.FileCategoryEnum;
import com.future.csamsserver.enumeration.TargetTypeEnum;
import com.future.csamsserver.mapper.LocalFileMapper;
import com.future.csamsserver.utils.U;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.ServletContext;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * 统一文件表(LocalFile)表控制层
 *
 * @author Jenming
 * @since 2025-03-16 00:02:00
 */
@Slf4j
@RestController
public class FileUploadController {

    // 注入基本值
    @Value("${file.upload.path}")
    private String uploadPath;

    // 带默认值的注入
    @Value("${file.max-size:5MB}")
    private String maxFileSize;

    // 注入数组/列表
    @Value("#{'${allowed.types}'.split(',')}")
    private List<String> allowedFileTypes;

    private final ServletContext servletContext;
    private final LocalFileMapper localFileMapper;

    public FileUploadController(LocalFileMapper localFileMapper, ServletContext servletContext) {
        this.localFileMapper = localFileMapper;
        this.servletContext = servletContext;
    }
    /*
     * Deprecated
     * */
//    @SneakyThrows
//    @PostMapping("/upload")
//    public SaResult uploadFiles(
//            @RequestParam("file") MultipartFile[] files,
//            @RequestParam int targetType,
//            @RequestParam long targetId,
//            @RequestParam int fileCategory) {
//        /*
//         * MaxUploadSizeExceededException大文件配置报错
//         * file为null一样执行
//         * */
//
//        final ArrayList<LocalFile> localFiles = new ArrayList<>();
//        for (MultipartFile file : files) {
////            System.err.println("=============================");
////            System.err.println(file.getOriginalFilename());
////            System.err.println(file.getName());
////            a.txt
////                    files
//            final LocalFile localFile = new LocalFile();
//            localFile.setTargetType(TargetTypeEnum.of(targetType));
//            localFile.setTargetId(targetId);
//            final String originalFilename = file.getOriginalFilename();
//            final String fileUri = uploadPath + "/" + UUID.randomUUID() + Objects.requireNonNull(originalFilename).substring(originalFilename.lastIndexOf("."));
//            localFile.setFileUri(fileUri);
//            localFile.setFileName(originalFilename);
//            localFile.setFileType(file.getContentType());
//            localFile.setFileCategory(FileCategoryEnum.of(fileCategory));
//            /*
//             * byte
//             * */
//            localFile.setFileSize(file.getSize());
//            localFiles.add(localFile);
//            /*
//             * 工作目录+上下文路径+自定义路径=文件父路径
//             * */
////            csams/file/file
////            String serverUploadPath = Paths.get(System.getProperty("user.dir"), servletContext.getContextPath(), uploadPath).toString();
//            String serverUploadPath = Paths.get(System.getProperty("user.dir")).toString();
//            /*
//             * Windows：返回形如 C:\projects\app
//             * Linux：返回形如 /home/user/app 或 /opt/app
//             * Path.of()也可获得拼接字符串
//             * */
//            Path targetPath = Paths.get(serverUploadPath, localFile.getFileUri());
//            /*
//             * 确保目录存在
//             * */
//            Files.createDirectories(targetPath.getParent());
//            Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);
//        }

    /// /        Arrays.asList(files),
//        localFileMapper.insertOrUpdate(localFiles);
//        return SaResult.data(localFiles);
//    }


// 新增磁盘空间检查方法
    private void checkDiskSpace(long fileSize) throws IOException {
        Path storagePath = Paths.get(uploadPath);
        FileStore store = Files.getFileStore(storagePath);
        if (store.getUsableSpace() < fileSize * 2) { // 保留双倍空间缓冲
            throw new IOException("磁盘空间不足");
        }
    }

    // 新增文件回滚方法
    private void rollbackFiles(List<Path> storedFiles) {
        storedFiles.forEach(path -> {
            try {
                Files.deleteIfExists(path);
                log.info("已回滚文件：{}", path); // 审计日志
            } catch (IOException e) {
                log.error("文件回滚失败：{}", path, e);
            }
        });
    }

    @SneakyThrows
    @PostMapping("/upload")
    @Transactional(rollbackFor = Exception.class) // 添加事务注解
    public SaResult uploadFiles(
            @RequestParam("file") MultipartFile[] files,
            @RequestParam Integer targetType,
            @RequestParam Long targetId,
            @RequestParam Integer fileCategory) {
        /*
         * 检测展示新闻稿是否独一份
         * */
        if (fileCategory != null && fileCategory == FileCategoryEnum.SHOW_PRESS_RELEASE.getCode()) {
            final LambdaQueryWrapper<LocalFile> wrapper = new LambdaQueryWrapper<>();
            wrapper
                    .eq(targetType != null, LocalFile::getTargetType, targetType)
                    .eq(targetId != null, LocalFile::getTargetId, targetId)
                    .eq(true, LocalFile::getFileCategory, FileCategoryEnum.SHOW_PRESS_RELEASE.getCode());
            final LocalFile localFile = localFileMapper.selectOne(wrapper);
            if (localFile != null) {
                /*
                 * 删掉旧的
                 * */
                final SaResult saResult = U.deleteLocalFile(localFileMapper, localFile);
                /*
                 * 删除不成功直接返回，反之成功继续执行
                 * 前端校验200，而不是U.DELETE_LOCAL_FILE_SUCCESS
                 * */
                if (!Objects.equals(saResult.getCode(), 200))
                    return saResult;
            }
        }

        final ArrayList<LocalFile> localFiles = new ArrayList<>();
        final List<Path> storedFiles = new ArrayList<>(); // 记录已存储文件

        try {
            for (MultipartFile file : files) {
                // 磁盘空间检查（新增）
                checkDiskSpace(file.getSize());

                final LocalFile localFile = new LocalFile();
                localFile.setTargetType(TargetTypeEnum.of(targetType));
                localFile.setTargetId(targetId);
                final String originalFilename = file.getOriginalFilename();
                final String fileUri = uploadPath + "/" + UUID.randomUUID() + Objects.requireNonNull(originalFilename).substring(originalFilename.lastIndexOf("."));
                localFile.setFileUri(fileUri);
                localFile.setFileName(originalFilename);
                localFile.setFileType(file.getContentType());
                localFile.setFileCategory(FileCategoryEnum.of(fileCategory));
                /*
                 * byte
                 * */
                localFile.setFileSize(file.getSize());
                localFiles.add(localFile);
                Path targetPath = Path.of(System.getProperty("user.dir"), localFile.getFileUri());
                /*
                 * 确保目录存在
                 * */
                Files.createDirectories(targetPath.getParent());
                Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);
                // 存储文件成功后记录路径
                storedFiles.add(targetPath);
            }

            // 数据库操作（失败会触发事务回滚）
            localFileMapper.insertOrUpdate(localFiles);
            // 在 insertOrUpdate 后添加模拟异常
//            throw new RuntimeException("模拟数据库故障");

            log.info("文件上传成功，数量：{}", files.length); // 审计日志
            return SaResult.data(localFiles);


        } catch (Exception e) {
            // 回滚文件（新增）
            rollbackFiles(storedFiles);

            // 审计日志（新增）
            log.error("文件上传失败：{}", e.getMessage());

            if (e instanceof DataAccessException) {
                return SaResult.error("数据库操作失败");
            }
            return SaResult.error("上传失败：" + e.getMessage());
        }
    }

    @PostConstruct
    public void init() {
        log.error("File upload path: {}", Paths.get(System.getProperty("user.dir"), uploadPath));
    }

    private String getFileSuffixWithDot(String fileName) {
        // 方案 1：正确转义正则表达式（推荐）
        String[] parts = fileName.split("\\.");
        String extension = parts[parts.length - 1];

// 方案 2：使用 Pattern.quote 避免特殊字符问题
        String[] parts2 = fileName.split(Pattern.quote("."));
        String extension2 = parts[parts.length - 1];

// 方案 3：更健壮的后缀获取方法（处理没有后缀的情况）
        int lastDotIndex = fileName.lastIndexOf('.');
        String extension3 = (lastDotIndex == -1) ? "" : fileName.substring(lastDotIndex + 1);
/*
*
当文件名没有后缀时（如 README），方案 1/2 会返回整个文件名作为唯一元素
对于隐藏文件（如 .gitignore），方案 3 能正确返回空字符串
多个后缀的情况（如 tar.gz）会返回最后一个 gz
* */
        return null;
    }
}
