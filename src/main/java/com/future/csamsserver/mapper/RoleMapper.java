package com.future.csamsserver.mapper;

import com.future.csamsserver.domain.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.time.LocalDateTime;
import java.util.List;

/**
* @author Jenming
* @description 针对表【role(角色表)】的数据库操作Mapper
* @createDate 2025-03-04 21:50:17
* @Entity com.future.csamsserver.domain.Role
*/
public interface RoleMapper extends BaseMapper<Role> {
    List<String> selectRoleNameByUserId(long userId);

    List<Role> selectRoleListByParams(String roleName, String description, Long current, Long size, LocalDateTime createTimeStart, LocalDateTime createTimeEnd);

    List<Long> selectRoleIdListByUserId(Long userId);
}




