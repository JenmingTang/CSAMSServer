package com.future.csamsserver.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description
 * @dateTime 2025:03:06 19:24
 * @user Jenming
 */

// 4. Mapper 接口 ----------------------------------------------
@Mapper
public interface RolePermissionMapper {
    // 删除指定角色的权限关联
    int deleteByRoleIdAndPermissionIds(@Param("roleId") Long roleId,
                                       @Param("permissionIds") List<Long> permissionIds);

    // 批量插入角色-权限关联
    int batchInsertByRoleId(@Param("roleId") Long roleId,
                            @Param("permissionIds") List<Long> permissionIds);
}
