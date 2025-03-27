package com.future.csamsserver.mapper;

import com.future.csamsserver.domain.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

/**
* @author Jenming
* @description 针对表【permission(权限表)】的数据库操作Mapper
* @createDate 2025-03-04 21:50:17
* @Entity com.future.csamsserver.domain.Permission
*/
public interface PermissionMapper extends BaseMapper<Permission> {

    List<String> selectPermissionCodeByUserId(long userId);

    List<Permission> selectPermissionListByParams(String permCode, String description, Long current, Long size, LocalDateTime createTimeStart, LocalDateTime createTimeEnd);

    List<Long> selectPermissionIdListByRoleId(Long roleId);


}




