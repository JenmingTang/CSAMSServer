package com.future.csamsserver.mapper;

import com.future.csamsserver.domain.ClubMember;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.future.csamsserver.domain.vo.ClubMemberSAVO;

import java.time.LocalDateTime;
import java.util.List;

/**
* @author Jenming
* @description 针对表【club_member(社团成员表)】的数据库操作Mapper
* @createDate 2025-03-08 22:03:14
* @Entity com.future.csamsserver.domain.ClubMember
*/
public interface ClubMemberMapper extends BaseMapper<ClubMember> {

    List<ClubMemberSAVO> selectClubMemberListByParams(Long current, Long size, LocalDateTime createTimeStart, LocalDateTime
            createTimeEnd,Integer approvalStatus, String userName, String clubName, String approverName);
}




