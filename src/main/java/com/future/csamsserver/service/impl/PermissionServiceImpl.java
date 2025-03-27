package com.future.csamsserver.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.future.csamsserver.domain.Permission;
import com.future.csamsserver.service.PermissionService;
import com.future.csamsserver.mapper.PermissionMapper;
import org.springframework.stereotype.Service;

/**
* @author Jenming
* @description 针对表【permission(权限表)】的数据库操作Service实现
* @createDate 2025-03-04 21:50:17
*/
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission>
    implements PermissionService{

}




