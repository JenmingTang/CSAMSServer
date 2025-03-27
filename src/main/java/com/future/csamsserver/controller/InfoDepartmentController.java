package com.future.csamsserver.controller;

import cn.dev33.satoken.util.SaResult;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.future.csamsserver.domain.InfoDepartment;
import com.future.csamsserver.mapper.InfoDepartmentMapper;
import com.future.csamsserver.service.InfoDepartmentService;
import com.future.csamsserver.utils.U;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 部门表(InfoDepartment)表控制层
 *
 * @author Jenming
 * @since 2025-03-07 18:07:13
 */
@Slf4j
@RestController
@RequestMapping("infoDepartment")
public class InfoDepartmentController {

    private final InfoDepartmentMapper infoDepartmentMapper;
    private final InfoDepartmentService infoDepartmentService;

    public InfoDepartmentController(InfoDepartmentMapper infoDepartmentMapper, InfoDepartmentService infoDepartmentService) {
        this.infoDepartmentMapper = infoDepartmentMapper;
        this.infoDepartmentService = infoDepartmentService;
    }

    @GetMapping("selectInfoDepartmentListByParams")
    public SaResult selectInfoDepartmentListByParams(
            @RequestParam(defaultValue = "1") Long current,
            @RequestParam(defaultValue = "10") Long size,
            @RequestParam(required = false) LocalDateTime createTimeStart,
            @RequestParam(required = false) LocalDateTime createTimeEnd,
            @RequestParam(required = false) String code,
            @RequestParam(required = false) String name
    ) {
        log.info("selectInfoDepartmentListByParams: current={}, size={}, createTimeStart={}, createTimeEnd={}", current, size, createTimeStart, createTimeEnd);
        if (current != null && current == 1) {
            current = 0L;
        }
        final PageDTO<InfoDepartment> pageDTO = new PageDTO<>(current, size);
        LambdaQueryWrapper<InfoDepartment> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper
//                .eq(deleted != null && deleted >= 0, InfoDepartment::getDeleted, deleted)
                .ge(createTimeStart != null, InfoDepartment::getCreateTime, createTimeStart)
                .le(createTimeEnd != null, InfoDepartment::getCreateTime, createTimeEnd)
                .orderByDesc(InfoDepartment::getId)
                .like(code != null, InfoDepartment::getCode, code)
                .like(name != null, InfoDepartment::getName, name)
        ;
        return SaResult.data(U.wrapAsSAResult(current+1 ,size,infoDepartmentMapper.selectCount(null),infoDepartmentMapper.selectList(pageDTO,lambdaQueryWrapper)));
//        return SaResult.data(U.wrapAsSAResult(current + 1, size, infoDepartmentMapper.selectInfoDepartmentListByParams(current, size, createTimeStart, createTimeEnd
//        ,code,name)));
    }

    @DeleteMapping("deleteInfoDepartmentById/{id}")
    public SaResult deleteInfoDepartmentById(
            @PathVariable("id") Long id) {
        log.info("deleteInfoDepartmentById: {}", id);
        return SaResult.data(infoDepartmentMapper.deleteById(id));
    }

    @DeleteMapping("deleteInfoDepartmentListByIdList")
    public SaResult deleteInfoDepartmentListByIdList(
            @RequestBody List<Long> idList) {
        log.info("deleteInfoDepartmentListByIdList: {}", idList);
        return SaResult.data(infoDepartmentMapper.deleteByIds(idList));
    }

    @PostMapping("insertOrUpdateInfoDepartment")
    public SaResult insertOrUpdateInfoDepartment(
            @RequestBody InfoDepartment infoDepartment) {
        log.info("insertOrUpdateInfoDepartment: {}", infoDepartment);
        return SaResult.data(infoDepartmentMapper.insertOrUpdate(infoDepartment));

    }


    @GetMapping("selectInfoDepartmentList")
    public SaResult selectInfoDepartmentList() {
        log.info("selectInfoDepartmentList");
        return SaResult.data(infoDepartmentMapper.selectList(null));
    }
}
