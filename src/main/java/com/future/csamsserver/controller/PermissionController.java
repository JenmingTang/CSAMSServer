package com.future.csamsserver.controller;

import cn.dev33.satoken.util.SaResult;

import com.future.csamsserver.domain.Permission;
import com.future.csamsserver.mapper.PermissionMapper;
import com.future.csamsserver.utils.U;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 权限表(Permission)表控制层
 *
 * @author Jenming
 * @since 2025-03-06 15:40:40
 */
@Slf4j
@RestController
@RequestMapping("permission")
public class PermissionController {

    private final PermissionMapper permissionMapper;

    public PermissionController(PermissionMapper permissionMapper) {
        this.permissionMapper = permissionMapper;
    }

    @GetMapping("selectPermissionListByParams")
    public SaResult selectPermissionListByParams(
            @RequestParam(required = false) String permCode,
            @RequestParam(required = false) String description,
            @RequestParam(defaultValue = "1") Long current,
            @RequestParam(defaultValue = "10") Long size,
            @RequestParam(required = false) LocalDateTime createTimeStart,
            @RequestParam(required = false) LocalDateTime createTimeEnd
    ) {
        log.info("selectPermissionListByParams: current={}, size={}, createTimeStart={}, createTimeEnd={}, permCode={}, description={}", permCode,description,current, size, createTimeStart, createTimeEnd);
        if (current != null && current == 1) {
            current = 0L;
        }
        return SaResult.data(U.wrapAsSAResult(current + 1, size,permissionMapper.selectCount(null), permissionMapper.selectPermissionListByParams(permCode,description,current, size, createTimeStart, createTimeEnd)));
    }

    @DeleteMapping("deletePermissionById/{id}")
    public SaResult deletePermissionById(
            @PathVariable("id") Long id) {
        log.info("deletePermissionById: {}", id);
        return SaResult.data(permissionMapper.deleteById(id));
    }

    @DeleteMapping("deletePermissionListByIdList")
    public SaResult deletePermissionListByIdList(
            @RequestBody List<Long> idList) {
        log.info("deletePermissionListByIdList: {}", idList);
        return SaResult.data(permissionMapper.deleteByIds(idList));
    }

    @PostMapping("insertOrUpdatePermission")
    public SaResult insertOrUpdatePermission(
            @RequestBody Permission permission) {
        log.info("insertOrUpdatePermission: {}", permission);
        return SaResult.data(permissionMapper.insertOrUpdate(permission));
    }


    @GetMapping("selectPermissionList")
    public SaResult selectPermissionList() {
        log.info("selectPermissionList");
        return SaResult.data(permissionMapper.selectList(null));
    }
    @GetMapping("selectPermissionIdListByRoleId")
    public SaResult selectPermissionIdListByRoleId(@RequestParam("userId") Long roleId) {
        log.info("selectPermissionIdListByRoleId: roleId={}",roleId);
        return SaResult.data(permissionMapper.selectPermissionIdListByRoleId(roleId));
    }
}
