package com.future.csamsserver.controller;

import cn.dev33.satoken.util.SaResult;

import com.future.csamsserver.domain.Role;
import com.future.csamsserver.mapper.RoleMapper;
import com.future.csamsserver.utils.U;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 角色表(Role)表控制层
 *
 * @author Jenming
 * @since 2025-03-06 00:09:23
 */
@Slf4j
@RestController
@RequestMapping("role")
public class RoleController {

    private final RoleMapper roleMapper;

    public RoleController(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    @GetMapping("selectRoleListByParams")
    public SaResult selectRoleListByParams(
            @RequestParam(required = false) String roleName,
            @RequestParam(required = false) String description,
            @RequestParam(defaultValue = "1") Long current,
            @RequestParam(defaultValue = "10") Long size,
            @RequestParam(required = false) LocalDateTime createTimeStart,
            @RequestParam(required = false) LocalDateTime createTimeEnd
            ) {
        log.info("selectRoleListByParams: current={}, size={}, createTimeStart={}, createTimeEnd={}, roleName={}, description={}",roleName,description, current, size, createTimeStart, createTimeEnd);
        if (current != null && current == 1) {
            current = 0L;
        }
        return SaResult.data(U.wrapAsSAResult(current + 1, size, roleMapper.selectCount(null),roleMapper.selectRoleListByParams(roleName,description,current, size, createTimeStart, createTimeEnd)));
    }

    @DeleteMapping("deleteRoleById/{id}")
    public SaResult deleteRoleById(
            @PathVariable("id") Long id) {
        log.info("deleteRoleById: {}", id);
        return SaResult.data(roleMapper.deleteById(id));
    }

    @DeleteMapping("deleteRoleListByIdList")
    public SaResult deleteRoleListByIdList(
            @RequestBody List<Long> idList) {
        log.info("deleteRoleListByIdList: {}", idList);
        return SaResult.data(roleMapper.deleteByIds(idList));
    }

    @PostMapping("insertOrUpdateRole")
    public SaResult insertOrUpdateRole(
            @RequestBody Role role) {
        log.info("insertOrUpdateRole: {}", role);
        return SaResult.data(roleMapper.insertOrUpdate(role));
    }


    @GetMapping("selectRoleList")
    public SaResult selectRoleList() {
        log.info("selectRoleList");
        return SaResult.data(roleMapper.selectList(null));
    }
    @GetMapping("selectRoleIdListByUserId/{userId}")
    public SaResult selectRoleIdListByUserId(@PathVariable("userId") Long userId) {
        return SaResult.data(roleMapper.selectRoleIdListByUserId(userId));
    }
}
