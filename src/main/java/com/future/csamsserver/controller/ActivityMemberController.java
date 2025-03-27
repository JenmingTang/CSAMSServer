package com.future.csamsserver.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;

import com.future.csamsserver.domain.ActivityMember;
import com.future.csamsserver.enumeration.ApprovalStatusEnum;
import com.future.csamsserver.mapper.ActivityMapper;
import com.future.csamsserver.mapper.ActivityMemberMapper;
import com.future.csamsserver.mapper.ClubMapper;
import com.future.csamsserver.utils.U;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * 活动成员表(ActivityMember)表控制层
 *
 * @author Jenming
 * @since 2025-03-10 22:01:03
 */
@Slf4j
@RestController
@RequestMapping("activityMember")
public class ActivityMemberController {

    private final ActivityMemberMapper activityMemberMapper;
    private final ActivityMapper activityMapper;
    private final ClubMapper clubMapper;

    public ActivityMemberController(ActivityMemberMapper activityMemberMapper, ActivityMapper activityMapper, ClubMapper clubMapper) {
        this.activityMemberMapper = activityMemberMapper;
        this.activityMapper = activityMapper;
        this.clubMapper = clubMapper;
    }

    @GetMapping("selectActivityMemberListByParams")
    public SaResult selectActivityMemberListByParams(
            @RequestParam(defaultValue = "1") Long current,
            @RequestParam(defaultValue = "10") Long size,
            @RequestParam(required = false) LocalDateTime createTimeStart,
            @RequestParam(required = false) LocalDateTime createTimeEnd,
            @RequestParam(required = false) String userName,
            @RequestParam(required = false) String activityName,
            @RequestParam(required = false) LocalDateTime joinTime,
            @RequestParam(required = false) Integer approvalStatus,
            @RequestParam(required = false) String approverName

    ) {
        log.info("selectActivityMemberListByParams: current={}, size={}, createTimeStart={}, createTimeEnd={}, userName={}, activityName={}, joinTime={}, approvalStatus={}, approverName={}",
                current, size, createTimeStart, createTimeEnd, userName, activityName, joinTime, approvalStatus, approverName);
        if (current != null && current == 1) {
            current = 0L;
        }
        return SaResult.data(U.wrapAsSAResult(current + 1, size, activityMemberMapper.selectCount(null),
                activityMemberMapper.selectActivityMemberListByParams(
                        current, size, createTimeStart, createTimeEnd, userName, activityName, joinTime, approvalStatus, approverName)));
    }

    @DeleteMapping("deleteActivityMemberById/{id}")
    public SaResult deleteActivityMemberById(
            @PathVariable("id") Long id) {
        log.info("deleteActivityMemberById: {}", id);
        return SaResult.data(activityMemberMapper.deleteById(id));
    }

    @DeleteMapping("deleteActivityMemberListByIdList")
    public SaResult deleteActivityMemberListByIdList(
            @RequestBody List<Long> idList) {
        log.info("deleteActivityMemberListByIdList: {}", idList);
        return SaResult.data(activityMemberMapper.deleteByIds(idList));
    }

    @PostMapping("insertOrUpdateActivityMember")
    public SaResult insertOrUpdateActivityMember(
            @RequestBody ActivityMember activityMember) {
        log.info("insertOrUpdateActivityMember: {}", activityMember);
        if (activityMember.getId() == null){
            activityMember.setUserId(StpUtil.getLoginIdAsLong());
            /*
            * 由活动所属社团负责人审批，不由活动创始人审批
            * */
            final Long clubId = activityMapper.selectById(activityMember.getActivityId()).getClubId();
            final Long founderId = clubMapper.selectById(clubId).getFounderId();
            activityMember.setApproverId(founderId);
            activityMember.setApprovalStatus(ApprovalStatusEnum.UNAPPROVED.getCode());
        }
        /*
         * 审批时间随更新而改变
         * */
        if (!Objects.equals(activityMember.getApprovalStatus(), ApprovalStatusEnum.UNAPPROVED.getCode())){
            activityMember.setApproveTime(LocalDateTime.now());
        }
        /*
         * 审批通过修改加入时间
         * */
        if (Objects.equals(activityMember.getApprovalStatus(), ApprovalStatusEnum.APPROVED.getCode())) {
            activityMember.setJoinTime(LocalDateTime.now());
        }
        log.info("insertOrUpdateActivityMember end: {}", activityMember);
        return SaResult.data(activityMemberMapper.insertOrUpdate(activityMember));
    }


    @GetMapping("selectActivityMemberList")
    public SaResult selectActivityMemberList() {
        log.info("selectActivityMemberList");
        return SaResult.data(activityMemberMapper.selectList(null));
    }
}
