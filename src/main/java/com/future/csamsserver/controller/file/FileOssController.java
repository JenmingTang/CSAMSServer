package com.future.csamsserver.controller.file;

import cn.dev33.satoken.util.SaResult;

import com.future.csamsserver.domain.FileOss;
import com.future.csamsserver.mapper.FileOssMapper;
//import com.future.csamsserver.utils.U;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 统一文件表(FileOss)表控制层
 *
 * @author Jenming
 * @since 2025-03-13 00:09:56
 */
@Slf4j
@RestController
@RequestMapping("fileOss")
public class FileOssController {

    private final FileOssMapper fileOssMapper;

    public FileOssController(FileOssMapper fileOssMapper) {
        this.fileOssMapper = fileOssMapper;
    }
/*

            @RequestParam(required = false) Integer targetTypeEnum
//            StringToTargetTypeEnumConverter可全局
//            @RequestParam(required = false) TargetTypeEnum targetTypeEnum
    ) {
//        final TargetTypeEnum targetTypeEnum1 = TargetTypeEnum.of(targetTypeEnum);
* */
//    @GetMapping("selectFileOssListByParams")
//    public SaResult selectFileOssListByParams(
//            @RequestParam(defaultValue = "1") Long current,
//            @RequestParam(defaultValue = "10") Long size,
//            @RequestParam(required = false) LocalDateTime createTimeStart,
//            @RequestParam(required = false) LocalDateTime createTimeEnd
//    ) {
//        log.info("selectFileOssListByParams: current={}, size={}, createTimeStart={}, createTimeEnd={}", current, size, createTimeStart, createTimeEnd);
//        if (current != null && current == 1) {
//            current = 0L;
//        }
//        return SaResult.data(U.wrapAsSAResult(current + 1, size, fileOssMapper.selectCount(null), fileOssMapper.selectFileOssListByParams(current, size, createTimeStart, createTimeEnd)));
//    }

    @DeleteMapping("deleteFileOssById/{id}")
    public SaResult deleteFileOssById(
            @PathVariable("id") Long id) {
        log.info("deleteFileOssById: {}", id);
        return SaResult.data(fileOssMapper.deleteById(id));
    }

    @DeleteMapping("deleteFileOssListByIdList")
    public SaResult deleteFileOssListByIdList(
            @RequestBody List<Long> idList) {
        log.info("deleteFileOssListByIdList: {}", idList);
        return SaResult.data(fileOssMapper.deleteByIds(idList));
    }

    @PostMapping("insertOrUpdateFileOss")
    public SaResult insertOrUpdateFileOss(
            @RequestBody FileOss fileOss) {
        log.info("insertOrUpdateFileOss: {}", fileOss);
        return SaResult.data(fileOssMapper.insertOrUpdate(fileOss));
    }


    @GetMapping("selectFileOssList")
    public SaResult selectFileOssList() {
        log.info("selectFileOssList");
        return SaResult.data(fileOssMapper.selectList(null));
    }
}
