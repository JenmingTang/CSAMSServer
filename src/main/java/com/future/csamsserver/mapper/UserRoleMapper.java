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
public interface UserRoleMapper {

    // 删除用户所有角色关联
    @Delete("DELETE FROM user_role WHERE user_id = #{userId}")
    int deleteByUserId(@Param("userId") Long userId);

    // 批量插入新角色
    void batchInsert(@Param("userId") Long userId,
                     @Param("roleIds") List<Long> roleIds);
}
