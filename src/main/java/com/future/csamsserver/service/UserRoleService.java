package com.future.csamsserver.service;

import com.future.csamsserver.domain.dto.UserRoleUpdateDTO;
import com.future.csamsserver.mapper.RoleMapper;
import com.future.csamsserver.mapper.UserMapper;
import com.future.csamsserver.mapper.UserRoleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @description
 * @dateTime 2025:03:06 19:24
 * @user Jenming
 */

// 3. Service 层 -----------------------------------------------
@Service
@RequiredArgsConstructor
public class UserRoleService {

    private final UserRoleMapper userRoleMapper;
    private final UserMapper userMapper;
    private final RoleMapper roleMapper;

    @Transactional
    public void updateUserRoles(UserRoleUpdateDTO dto) {
//        // 校验用户是否存在
//        if (!userMapper.existsById(dto.getUserId())) {
//            throw new BusinessException("用户不存在");
//        }
//
//        // 校验角色列表有效性
//        if (!roleMapper.existsAllById(dto.getRoleIds())) {
//            throw new BusinessException("包含无效角色ID");
//        }

        // 删除旧角色关联
        userRoleMapper.deleteByUserId(dto.getUserId());

        // 插入新角色关联（批量操作）
        if (!dto.getRoleIds().isEmpty()) {
            userRoleMapper.batchInsert(dto.getUserId(), dto.getRoleIds());
        }
    }
}
