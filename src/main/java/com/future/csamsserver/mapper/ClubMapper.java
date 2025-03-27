package com.future.csamsserver.mapper;

import com.future.csamsserver.domain.Club;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.future.csamsserver.domain.vo.ClubSAVO;

import java.time.LocalDateTime;
import java.util.List;

/**
* @author Jenming
* @description 针对表【club(社团表)】的数据库操作Mapper
* @createDate 2025-03-08 22:03:14
* @Entity com.future.csamsserver.domain.Club
*/
public interface ClubMapper extends BaseMapper<Club> {

    List<ClubSAVO> selectClubListByParams(Long current, Long size, LocalDateTime createTimeStart, LocalDateTime createTimeEnd, String name, Integer approvalStatus,String approverName);

}




