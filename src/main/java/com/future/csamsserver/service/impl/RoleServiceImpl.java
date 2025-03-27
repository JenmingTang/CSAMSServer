package com.future.csamsserver.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.future.csamsserver.domain.Role;
import com.future.csamsserver.service.RoleService;
import com.future.csamsserver.mapper.RoleMapper;
import org.springframework.stereotype.Service;

/**
* @author Jenming
* @description 针对表【role(角色表)】的数据库操作Service实现
* @createDate 2025-03-04 21:50:17
*/
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
    implements RoleService{

}




