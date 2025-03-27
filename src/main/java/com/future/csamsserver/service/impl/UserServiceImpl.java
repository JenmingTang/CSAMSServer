package com.future.csamsserver.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.future.csamsserver.domain.User;
import com.future.csamsserver.service.UserService;
import com.future.csamsserver.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author Jenming
* @description 针对表【user(用户表)】的数据库操作Service实现
* @createDate 2025-03-04 22:19:52
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




