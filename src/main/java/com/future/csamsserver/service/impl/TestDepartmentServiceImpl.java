package com.future.csamsserver.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.future.csamsserver.domain.TestDepartment;
import com.future.csamsserver.service.TestDepartmentService;
import com.future.csamsserver.mapper.TestDepartmentMapper;
import org.springframework.stereotype.Service;

/**
* @author Jenming
* @description 针对表【test_department(部门表)】的数据库操作Service实现
* @createDate 2025-03-19 22:10:57
*/
@Service
public class TestDepartmentServiceImpl extends ServiceImpl<TestDepartmentMapper, TestDepartment>
    implements TestDepartmentService{

}




