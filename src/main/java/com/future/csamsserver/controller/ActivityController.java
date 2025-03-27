package com.future.csamsserver.controller;

import cn.dev33.satoken.util.SaResult;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.future.csamsserver.domain.Activity;
import com.future.csamsserver.domain.dto.ActivityTimeConflictEntity;
import com.future.csamsserver.enumeration.ApprovalStatusEnum;
import com.future.csamsserver.mapper.ActivityMapper;
import com.future.csamsserver.mapper.ClubMapper;
import com.future.csamsserver.utils.U;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * 活动表(Activity)表控制层
 *
 * @author Jenming
 * @since 2025-03-10 22:01:03
 */
@Slf4j
@RestController
@RequestMapping("activity")
public class ActivityController {

    private final ActivityMapper activityMapper;
    private final ClubMapper clubMapper;

    public ActivityController(ActivityMapper activityMapper, ClubMapper clubMapper) {
        this.activityMapper = activityMapper;
        this.clubMapper = clubMapper;
    }

    @GetMapping("selectActivityListByParams")
    public SaResult selectActivityListByParams(
            @RequestParam(defaultValue = "1") Long current,
            @RequestParam(defaultValue = "10") Long size,
            @RequestParam(required = false) LocalDateTime createTimeStart,
            @RequestParam(required = false) LocalDateTime createTimeEnd,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String clubName,
            @RequestParam(required = false) Integer approvalStatus,
            @RequestParam(required = false) String approverName
    ) {
        log.info("selectActivityListByParams: current={}, size={}, createTimeStart={}, createTimeEnd={}, title={}, clubName={}, approvalStatus={}, approverName={}",
                current, size, createTimeStart, createTimeEnd, title, clubName, approvalStatus, approverName);
        if (current != null && current == 1) {
            current = 0L;
        }
        return SaResult.data(U.wrapAsSAResult(current + 1, size, activityMapper.selectCount(null),
                activityMapper.selectActivityListByParams(current, size, createTimeStart, createTimeEnd, title, clubName, approvalStatus, approverName)));
    }

    @DeleteMapping("deleteActivityById/{id}")
    public SaResult deleteActivityById(
            @PathVariable("id") Long id) {
        log.info("deleteActivityById: {}", id);
        return SaResult.data(activityMapper.deleteById(id));
    }

    @DeleteMapping("deleteActivityListByIdList")
    public SaResult deleteActivityListByIdList(
            @RequestBody List<Long> idList) {
        log.info("deleteActivityListByIdList: {}", idList);
        return SaResult.data(activityMapper.deleteByIds(idList));
    }
//    private boolean hasTimeConflict(String location, LocalDateTime startTime, LocalDateTime endTime) {

    @PostMapping("insertOrUpdateActivity")
    public SaResult insertOrUpdateActivity(
            @RequestBody Activity activity) {
        log.info("insertOrUpdateActivity: {}", activity);
        /*
        * 处理场地是否冲突问题
        * */
        final List<ActivityTimeConflictEntity> activityTimeConflictEntityList = activityMapper.timeConflictEntity(activity.getLocation(),activity.getStartTime(),activity.getEndTime());
        /*
        * 存在时间重叠，返回提示用户避开重叠时间范围列表
        * */
        if (!activityTimeConflictEntityList.isEmpty()){
//            SaResult.code(HttpStatusCode.ACTIVITY_TIME_RANGE_CONFLICT);
//            return SaResult.data(activityTimeConflictEntityList);
//            return SaResult.code(HttpStatusCode.ACTIVITY_TIME_RANGE_CONFLICT).data(activityTimeConflictEntityList);
            return SaResult.data(activityTimeConflictEntityList);
        }
        if (activity.getId() == null) {
            /*
             * 审批人为活动所属社团负责人
             * */
            activity.setApproverId(clubMapper.selectById(activity.getClubId()).getFounderId());
            activity.setApprovalStatus(ApprovalStatusEnum.UNAPPROVED.getCode());
        }
        if (!Objects.equals(activity.getApprovalStatus(), ApprovalStatusEnum.UNAPPROVED.getCode())){
            activity.setApproveTime(LocalDateTime.now());
        }
        log.info("insertOrUpdateActivity end: {}", activity);
        return SaResult.data(activityMapper.insertOrUpdate(activity));
    }


    @GetMapping("selectActivityList")
    public SaResult selectActivityList() {
        log.info("selectActivityList");
        return SaResult.data(activityMapper.selectList(null));
    }

    @GetMapping("selectActivityListWithApproved")
    public SaResult selectActivityListWithApproved() {
        log.info("selectActivityListWithApproved");
        final LambdaQueryWrapper<Activity> activityLambdaQueryWrapper = new LambdaQueryWrapper<Activity>().eq(Activity::getApprovalStatus, ApprovalStatusEnum.APPROVED.getCode());
        return SaResult.data(activityMapper.selectList(activityLambdaQueryWrapper));
    }
}
