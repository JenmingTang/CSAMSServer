package com.future.csamsserver.mapper;

import com.future.csamsserver.domain.InfoClass;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.future.csamsserver.domain.vo.InfoClassSAVO;

import java.time.LocalDateTime;
import java.util.List;

/**
* @author Jenming
* @description 针对表【info_class(班级表)】的数据库操作Mapper
* @createDate 2025-03-07 18:07:18
* @Entity com.future.csamsserver.domain.InfoClass
*/
public interface InfoClassMapper extends BaseMapper<InfoClass> {

    List<InfoClassSAVO> selectInfoClassListByParams(Long current, Long size, LocalDateTime createTimeStart, LocalDateTime createTimeEnd, Long majorId, String code, String name);

}




