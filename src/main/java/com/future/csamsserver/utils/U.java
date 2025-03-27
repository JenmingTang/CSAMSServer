package com.future.csamsserver.utils;

import cn.dev33.satoken.util.SaResult;
import com.future.csamsserver.domain.LocalFile;
import com.future.csamsserver.domain.sa.SAResult;
import com.future.csamsserver.mapper.LocalFileMapper;
import jdk.jfr.Unsigned;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

/**
 * @description
 * @dateTime 2025:03:04 23:02
 * @user Jenming
 */
@Slf4j
public class U {
    public static final Integer DELETE_LOCAL_FILE_SUCCESS = 1001;

    public static SaResult deleteLocalFile(LocalFileMapper localFileMapper, LocalFile localFile) {
        final Path realFilePath = Path.of(System.getProperty("user.dir"), localFile.getFileUri());
        try {
            Files.deleteIfExists(realFilePath);
        } catch (IOException e) {
            return SaResult.error("文件系统删除失败，请联系管理员！");
        }
        final int deleteById = localFileMapper.deleteById(localFile.getId());
        if (deleteById > 0) {

            /*
             * 删除不成功直接返回，反之成功继续执行
             * 前端校验200，而不是U.DELETE_LOCAL_FILE_SUCCESS
             * */
//            return SaResult.ok("删除成功！").setCode(DELETE_LOCAL_FILE_SUCCESS);
            return SaResult.ok("删除成功！");
        }
        return SaResult.error("数据库删除失败，请联系管理员！");
    }

    public static LocalDateTime[] parseDataTimeRange(Long[] dataTimeRange) {

        // 在方法体内添加处理逻辑
        LocalDateTime createTimeStart = null;
        LocalDateTime createTimeEnd = null;

        if (dataTimeRange != null && dataTimeRange.length == 2) {
            // 假设时间戳是毫秒级（若为秒级则用 ofEpochSecond）
            createTimeStart = LocalDateTime.ofInstant(
                    Instant.ofEpochMilli(dataTimeRange[0]),
                    ZoneId.systemDefault()
            );
            createTimeEnd = LocalDateTime.ofInstant(
                    Instant.ofEpochMilli(dataTimeRange[1]),
                    ZoneId.systemDefault()
            );
        } else if (dataTimeRange != null) {
            log.warn("Invalid dataTimeRange length: {}", dataTimeRange.length);
        }
        return new LocalDateTime[]{createTimeStart, createTimeEnd};
    }

    public static SaResult logInfo(String msg, Exception e) {
        log.info(msg, e);
        return SaResult.error("系统内部错误，请联系管理员！");
    }

    /**
     * @param currentPage
     * @param pageSize
     * @param sysRoleList
     * @param <T>
     * @return
     * @description SoybeanAdminResult
     * @dateTime 2024-12-06 22:30:47
     * @user Jenming
     */

    public static <T> SAResult<T> wrapAsSAResult(long currentPage, long pageSize, long total, List<T> sysRoleList) {
        SAResult<T> sysRoleSAResult = new SAResult<>();
        sysRoleSAResult.setRecords(sysRoleList);
        sysRoleSAResult.setCurrent(currentPage);
        sysRoleSAResult.setSize(pageSize);
        sysRoleSAResult.setTotal(total);
        return sysRoleSAResult;
    }
}
