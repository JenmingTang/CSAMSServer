package com.future.csamsserver.controller;

import cn.dev33.satoken.util.SaResult;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.future.csamsserver.domain.InfoMajor;
import com.future.csamsserver.domain.vo.InfoMajorSAVO;
import com.future.csamsserver.mapper.InfoDepartmentMapper;
import com.future.csamsserver.mapper.InfoMajorMapper;
import com.future.csamsserver.utils.U;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 专业表(InfoMajor)表控制层
 *
 * @author Jenming
 * @since 2025-03-07 18:07:13
 */
@Slf4j
@RestController
@RequestMapping("infoMajor")
public class InfoMajorController {

    private final InfoMajorMapper infoMajorMapper;
    private final InfoDepartmentMapper infoDepartmentMapper;

    public InfoMajorController(InfoMajorMapper infoMajorMapper, InfoDepartmentMapper infoDepartmentMapper) {
        this.infoMajorMapper = infoMajorMapper;
        this.infoDepartmentMapper = infoDepartmentMapper;
    }

    @GetMapping("selectInfoMajorListByParams")
    public SaResult selectInfoMajorListByParams(
            @RequestParam(defaultValue = "1") Long current,
            @RequestParam(defaultValue = "10") Long size,
            @RequestParam(required = false) LocalDateTime createTimeStart,
            @RequestParam(required = false) LocalDateTime createTimeEnd,
            @RequestParam(required = false) Long departmentId,
            @RequestParam(required = false) String code,
            @RequestParam(required = false) String name
    ) {
        log.info("selectInfoMajorListByParams: current={}, size={}, createTimeStart={}, createTimeEnd={}", current, size, createTimeStart, createTimeEnd);
        if (current != null && current == 1) {
            current = 0L;
        }

        final PageDTO<InfoMajor> pageDTO = new PageDTO<>(current, size);
        LambdaQueryWrapper<InfoMajor> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper
                .ge(createTimeStart != null, InfoMajor::getCreateTime, createTimeStart)
                .le(createTimeEnd != null, InfoMajor::getCreateTime, createTimeEnd)
                .orderByDesc(InfoMajor::getId)
                .eq(departmentId != null, InfoMajor::getDepartmentId, departmentId)
                .like(code != null, InfoMajor::getCode, code)
                .like(name != null, InfoMajor::getName, name)
        ;
        final List<InfoMajor> infoMajorList = infoMajorMapper.selectList(pageDTO, lambdaQueryWrapper);
        List<InfoMajorSAVO> infoMajorSAVOList = infoMajorList.stream().map(infoMajor -> {
            InfoMajorSAVO infoMajorSAVO = new InfoMajorSAVO();
            BeanUtils.copyProperties(infoMajor, infoMajorSAVO);
            String departmentName = infoDepartmentMapper.selectById(infoMajor.getDepartmentId()).getName();
            infoMajorSAVO.setDepartmentName(departmentName);
            return infoMajorSAVO;
        }).toList();
        return SaResult.data(U.wrapAsSAResult(current+1 ,size, infoMajorMapper.selectCount(null),infoMajorSAVOList));
    }

    @DeleteMapping("deleteInfoMajorById/{id}")
    public SaResult deleteInfoMajorById(
            @PathVariable("id") Long id) {
        log.info("deleteInfoMajorById: {}", id);
        return SaResult.data(infoMajorMapper.deleteById(id));
    }

    @DeleteMapping("deleteInfoMajorListByIdList")
    public SaResult deleteInfoMajorListByIdList(
            @RequestBody List<Long> idList) {
        log.info("deleteInfoMajorListByIdList: {}", idList);
        return SaResult.data(infoMajorMapper.deleteByIds(idList));
    }

    @PostMapping("insertOrUpdateInfoMajor")
    public SaResult insertOrUpdateInfoMajor(
            @RequestBody InfoMajor infoMajor) {
        log.info("insertOrUpdateInfoMajor: {}", infoMajor);
        return SaResult.data(infoMajorMapper.insertOrUpdate(infoMajor));
    }


    @GetMapping("selectInfoMajorList")
    public SaResult selectInfoMajorList() {
        log.info("selectInfoMajorList");
        return SaResult.data(infoMajorMapper.selectList(null));
    }
}
