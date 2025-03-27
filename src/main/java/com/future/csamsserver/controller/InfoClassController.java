package com.future.csamsserver.controller;

import cn.dev33.satoken.util.SaResult;

import com.future.csamsserver.domain.InfoClass;
import com.future.csamsserver.mapper.InfoClassMapper;
import com.future.csamsserver.utils.U;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 班级表(InfoClass)表控制层
 *
 * @author Jenming
 * @since 2025-03-07 18:07:13
 */
@Slf4j
@RestController
@RequestMapping("infoClass")
public class InfoClassController {

    private final InfoClassMapper infoClassMapper;

    public InfoClassController(InfoClassMapper infoClassMapper) {
        this.infoClassMapper = infoClassMapper;
    }

    @GetMapping("selectInfoClassListByParams")
    public SaResult selectInfoClassListByParams(
            @RequestParam(defaultValue = "1") Long current,
            @RequestParam(defaultValue = "10") Long size,
            @RequestParam(required = false) LocalDateTime createTimeStart,
            @RequestParam(required = false) LocalDateTime createTimeEnd,
            @RequestParam(required = false) Long majorId,
            @RequestParam(required = false) String code,
            @RequestParam(required = false) String name
    ) {
        log.info("selectInfoClassListByParams: current={}, size={}, createTimeStart={}, createTimeEnd={}", current, size, createTimeStart, createTimeEnd);
        if (current != null && current == 1) {
            current = 0L;
        }
        return SaResult.data(U.wrapAsSAResult(current + 1, size,infoClassMapper.selectCount(null), infoClassMapper.selectInfoClassListByParams(current, size, createTimeStart, createTimeEnd
        ,majorId,code,name)));
    }

    @DeleteMapping("deleteInfoClassById/{id}")
    public SaResult deleteInfoClassById(
            @PathVariable("id") Long id) {
        log.info("deleteInfoClassById: {}", id);
        return SaResult.data(infoClassMapper.deleteById(id));
    }

    @DeleteMapping("deleteInfoClassListByIdList")
    public SaResult deleteInfoClassListByIdList(
            @RequestBody List<Long> idList) {
        log.info("deleteInfoClassListByIdList: {}", idList);
        return SaResult.data(infoClassMapper.deleteByIds(idList));
    }

    @PostMapping("insertOrUpdateInfoClass")
    public SaResult insertOrUpdateInfoClass(
            @RequestBody InfoClass infoClass) {
        log.info("insertOrUpdateInfoClass: {}", infoClass);
        return SaResult.data(infoClassMapper.insertOrUpdate(infoClass));
    }


    @GetMapping("selectInfoClassList")
    public SaResult selectInfoClassList() {
        log.info("selectInfoClassList");
        return SaResult.data(infoClassMapper.selectList(null));
    }
}
