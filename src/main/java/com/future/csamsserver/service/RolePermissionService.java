package com.future.csamsserver.service;

import com.future.csamsserver.mapper.PermissionMapper;
import com.future.csamsserver.mapper.RolePermissionMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @description
 * @dateTime 2025:03:07 17:26
 * @user Jenming
 */
@Service
public class RolePermissionService {

    private final RolePermissionMapper rolePermissionMapper;
    private final PermissionMapper permissionMapper;

    public RolePermissionService(RolePermissionMapper rolePermissionMapper, PermissionMapper permissionMapper) {
        this.rolePermissionMapper = rolePermissionMapper;
        this.permissionMapper = permissionMapper;
    }

    @Transactional
public void updateRolePermissions(Long roleId, List<Long> newPermissionIds) {
    // 获取现有权限ID列表
    List<Long> existingIds = permissionMapper.selectPermissionIdListByRoleId(roleId);

    // 计算需要删除的权限
    List<Long> toDelete = existingIds.stream()
            .filter(id -> !newPermissionIds.contains(id))
            .collect(Collectors.toList());

    // 计算需要新增的权限
    List<Long> toAdd = newPermissionIds.stream()
            .filter(id -> !existingIds.contains(id))
            .collect(Collectors.toList());

    // 执行删除操作
    if (!toDelete.isEmpty()) {
        rolePermissionMapper.deleteByRoleIdAndPermissionIds(roleId, toDelete);
    }

    // 执行新增操作
    if (!toAdd.isEmpty()) {
        rolePermissionMapper.batchInsertByRoleId(roleId, toAdd);
    }
}

}
