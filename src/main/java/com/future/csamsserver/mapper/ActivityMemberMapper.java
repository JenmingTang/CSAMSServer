package com.future.csamsserver.mapper;

import com.future.csamsserver.domain.ActivityMember;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.future.csamsserver.domain.vo.ActivityMemberSAVO;

import java.time.LocalDateTime;
import java.util.List;

/**
* @author Jenming
* @description 针对表【activity_member(活动成员表)】的数据库操作Mapper
* @createDate 2025-03-10 22:00:51
* @Entity com.future.csamsserver.domain.ActivityMember
*/
public interface ActivityMemberMapper extends BaseMapper<ActivityMember> {

    List<ActivityMemberSAVO> selectActivityMemberListByParams(Long current, Long size, LocalDateTime createTimeStart, LocalDateTime createTimeEnd, String userName, String activityName, LocalDateTime joinTime, Integer approvalStatus, String approverName);
}




