package com.future.csamsserver.controller;

import cn.dev33.satoken.util.SaResult;

import com.future.csamsserver.domain.TestDepartment;
import com.future.csamsserver.mapper.TestDepartmentMapper;
import com.future.csamsserver.utils.U;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 部门表(TestDepartment)表控制层
 *
 * @author Jenming
 * @since 2025-03-19 22:11:08
 */
@Slf4j
@RestController
@RequestMapping("testDepartment")
public class TestDepartmentController {

    private final TestDepartmentMapper testDepartmentMapper;

    public TestDepartmentController(TestDepartmentMapper testDepartmentMapper) {
        this.testDepartmentMapper = testDepartmentMapper;
    }

//    @GetMapping("selectTestDepartmentListByParams")
//    public SaResult selectTestDepartmentListByParams(
//            @RequestParam(defaultValue = "1") Long current,
//            @RequestParam(defaultValue = "10") Long size,
//            @RequestParam(required = false) LocalDateTime createTimeStart,
//            @RequestParam(required = false) LocalDateTime createTimeEnd
//    ) {
//        log.info("selectTestDepartmentListByParams: current={}, size={}, createTimeStart={}, createTimeEnd={}", current, size, createTimeStart, createTimeEnd);
//        if (current != null && current == 1) {
//            current = 0L;
//        }
//        return SaResult.data(U.wrapAsSAResult(current + 1, size, testDepartmentMapper.selectCount(null), testDepartmentMapper.selectTestDepartmentListByParams(current, size, createTimeStart, createTimeEnd)));
//    }

    @DeleteMapping("deleteTestDepartmentById/{id}")
    public SaResult deleteTestDepartmentById(
            @PathVariable("id") Long id) {
        log.info("deleteTestDepartmentById: {}", id);
        return SaResult.data(testDepartmentMapper.deleteById(id));
    }

    @DeleteMapping("deleteTestDepartmentListByIdList")
    public SaResult deleteTestDepartmentListByIdList(
            @RequestBody List<Long> idList) {
        log.info("deleteTestDepartmentListByIdList: {}", idList);
        return SaResult.data(testDepartmentMapper.deleteByIds(idList));
    }

    @PostMapping("insertOrUpdateTestDepartment")
    public SaResult insertOrUpdateTestDepartment(
            @RequestBody TestDepartment testDepartment) {
        log.info("insertOrUpdateTestDepartment: {}", testDepartment);
        return SaResult.data(testDepartmentMapper.insertOrUpdate(testDepartment));
    }


    @GetMapping("selectTestDepartmentList")
    public SaResult selectTestDepartmentList() {
        log.info("selectTestDepartmentList");
        return SaResult.data(testDepartmentMapper.selectList(null));
    }
}
