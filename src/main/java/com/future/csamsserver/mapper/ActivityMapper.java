package com.future.csamsserver.mapper;

import com.future.csamsserver.domain.Activity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.future.csamsserver.domain.dto.ActivityTimeConflictEntity;
import com.future.csamsserver.domain.vo.ActivitySAVO;

import java.time.LocalDateTime;
import java.util.List;

/**
* @author Jenming
* @description 针对表【activity(活动表)】的数据库操作Mapper
* @createDate 2025-03-10 22:00:51
* @Entity com.future.csamsserver.domain.Activity
*/
public interface ActivityMapper extends BaseMapper<Activity> {

    List<ActivitySAVO> selectActivityListByParams(Long current, Long size, LocalDateTime createTimeStart, LocalDateTime createTimeEnd, String title, String clubName, Integer approvalStatus, String approverName);

    List<ActivityTimeConflictEntity> timeConflictEntity(String location, LocalDateTime startTime, LocalDateTime endTime);
}




