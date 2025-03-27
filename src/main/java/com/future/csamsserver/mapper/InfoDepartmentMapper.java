package com.future.csamsserver.mapper;

import com.future.csamsserver.domain.InfoDepartment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.time.LocalDateTime;
import java.util.List;

/**
* @author Jenming
* @description 针对表【info_department(部门表)】的数据库操作Mapper
* @createDate 2025-03-07 18:07:19
* @Entity com.future.csamsserver.domain.InfoDepartment
*/
public interface InfoDepartmentMapper extends BaseMapper<InfoDepartment> {

    List<InfoDepartment> selectInfoDepartmentListByParams(Long current, Long size, LocalDateTime createTimeStart, LocalDateTime createTimeEnd, String code, String name);
}




