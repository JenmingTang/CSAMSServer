package com.future.csamsserver.controller.file;

import cn.dev33.satoken.util.SaResult;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.future.csamsserver.domain.LocalFile;
import com.future.csamsserver.enumeration.DeletedEnum;
import com.future.csamsserver.enumeration.FileCategoryEnum;
import com.future.csamsserver.enumeration.TargetTypeEnum;
import com.future.csamsserver.mapper.LocalFileMapper;
import com.future.csamsserver.utils.U;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;

/**
 * 统一文件表(LocalFile)表控制层
 *
 * @author Jenming
 * @since 2025-03-16 00:02:00
 */
@Slf4j
@RestController
@RequestMapping("localFile")
public class LocalFileController {

    private final LocalFileMapper localFileMapper;

    public LocalFileController(LocalFileMapper localFileMapper
    ) {
        this.localFileMapper = localFileMapper;
    }

    @GetMapping("selectLocalFileListByParams")
    public SaResult selectLocalFileListByParams(
            @RequestParam Long targetId,
            @RequestParam(required = false) Integer targetType,
            @RequestParam(required = false) Integer fileCategory
    ) {
        log.info("selectLocalFileListByParams: targetType={}, targetId={}, fileCategory={}", targetType, targetId, fileCategory);
        /*
        * 实体字段为枚举，亦可OK
        * 不过xml就要取DeletedEnum.NORMAL.getCode()了
        *
        * 插入
  "targetType": "USER",不可
  "targetType": 0,可以
        *
        * */
//        final LambdaQueryWrapper<LocalFile> wrapper = new LambdaQueryWrapper<LocalFile>()
//                .eq(LocalFile::getTargetType, targetType)
//                .eq(LocalFile::getTargetId, targetId)
//                .eq(LocalFile::getFileCategory, fileCategory)
//                .eq(LocalFile::getDeleted, DeletedEnum.NORMAL.getCode());
/*
                会报，难道targetType != null是假的
                原因targetType != null比后面调用慢
                selectLocalFileListByParams: targetType=null, targetId=7, fileCategory=null
                Cannot invoke "java.lang.Integer.intValue()" because "targetType" is null
                .eq(targetType != null, LocalFile::getTargetType, TargetTypeEnum.of(targetType))


   public static TargetTypeEnum of(Integer code) {
       if (code == null) return null; // 或返回默认枚举值
       // 原有转换逻辑
   }


   .eq(targetType != null, LocalFile::getTargetType,
       Optional.ofNullable(targetType).map(TargetTypeEnum::of).orElse(null))


   .eq(targetType != null, LocalFile::getTargetType,
    TargetTypeEnum.of(Objects.requireNonNullElse(targetType, 0)))
* */
        final LambdaQueryWrapper<LocalFile> wrapper = new LambdaQueryWrapper<>();

        if (targetId != null) {
            wrapper.eq(LocalFile::getTargetId, targetId);
        }
        if (targetType != null) {
            wrapper.eq(LocalFile::getTargetType, TargetTypeEnum.of(targetType));
        }
        if (fileCategory != null) {
            wrapper.eq(LocalFile::getFileCategory, FileCategoryEnum.of(fileCategory));

        }
        wrapper.eq(LocalFile::getDeleted, DeletedEnum.NORMAL);
        return SaResult.data(localFileMapper.selectList(wrapper));
    }

    @DeleteMapping("deleteLocalFileById/{id}")
    public SaResult deleteLocalFileById(
            @PathVariable("id") Long id) {
        log.info("deleteLocalFileById: {}", id);
        final LocalFile localFile = localFileMapper.selectById(id);
        return U.deleteLocalFile(localFileMapper,localFile);
    }

    @DeleteMapping("deleteLocalFileListByIdList")
    public SaResult deleteLocalFileListByIdList(
            @RequestBody List<Long> idList) {
        log.info("deleteLocalFileListByIdList: {}", idList);
        return SaResult.data(localFileMapper.deleteByIds(idList));
    }

    @PostMapping("insertOrUpdateLocalFile")
    public SaResult insertOrUpdateLocalFile(
            @RequestBody LocalFile localFile) {
        log.info("insertOrUpdateLocalFile: {}", localFile);
        return SaResult.data(localFileMapper.insertOrUpdate(localFile));
    }


    @GetMapping("selectLocalFileList")
    public SaResult selectLocalFileList() {
        log.info("selectLocalFileList");
        return SaResult.data(localFileMapper.selectList(null));
    }
}
