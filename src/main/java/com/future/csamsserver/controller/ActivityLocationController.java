package com.future.csamsserver.controller;

import cn.dev33.satoken.util.SaResult;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.future.csamsserver.domain.ActivityLocation;
import com.future.csamsserver.enumeration.ApprovalStatusEnum;
import com.future.csamsserver.mapper.ActivityLocationMapper;
import com.future.csamsserver.mapper.UserMapper;
import com.future.csamsserver.utils.U;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * 活动场地表(ActivityLocation)表控制层
 *
 * @author Jenming
 * @since 2025-03-16 02:37:30
 */
@Slf4j
@RestController
@RequestMapping("activityLocation")
public class ActivityLocationController {

    private final ActivityLocationMapper activityLocationMapper;
    private final UserMapper userMapper;

    public ActivityLocationController(ActivityLocationMapper activityLocationMapper, UserMapper userMapper) {
        this.activityLocationMapper = activityLocationMapper;
        this.userMapper = userMapper;
    }

    /*
    // 方案一：修改请求参数格式（推荐）
@RequestParam(required = false) Long[] createTimeRange

// 对应前端请求URL格式：
?createTimeRange=1741104000000&createTimeRange=1741363200000

// 方案二：修改注解接收带方括号的参数 不可行
@RequestParam(value = "createTimeRange[]", required = false) Long[] createTimeRange
    * */
    @GetMapping("selectActivityLocationListByParams")
    public SaResult selectActivityLocationListByParams(
            @RequestParam(defaultValue = "1") Long current,
            @RequestParam(defaultValue = "10") Long size,
//            @RequestParam(value = "createTimeRange[]", required = false) Long[] createTimeRange,
            @RequestParam(required = false) LocalDateTime createTimeStart,
            @RequestParam(required = false) LocalDateTime createTimeEnd,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String approverName,
            @RequestParam(required = false) Integer approvalStatus
    ) {
//        log.info("selectActivityLocationListByParams: current={}, size={}, createTimeStart={}, createTimeEnd={}", current, size, createTimeStart, createTimeEnd);
        if (current != null && current == 1) {
            current = 0L;
        }
//        final LocalDateTime[] parsedDataTimeRange = U.parseDataTimeRange(createTimeRange);
        return SaResult.data(U.wrapAsSAResult(current + 1, size, activityLocationMapper.selectCount(null),
                activityLocationMapper.selectActivityLocationListByParams(
                        current, size, createTimeStart, createTimeEnd, name, approverName, approvalStatus)));
    }


    @DeleteMapping("deleteActivityLocationById/{id}")
    public SaResult deleteActivityLocationById(
            @PathVariable("id") Long id) {
        log.info("deleteActivityLocationById: {}", id);
        return SaResult.data(activityLocationMapper.deleteById(id));
    }

    @DeleteMapping("deleteActivityLocationListByIdList")
    public SaResult deleteActivityLocationListByIdList(
            @RequestBody List<Long> idList) {
        log.info("deleteActivityLocationListByIdList: {}", idList);
        return SaResult.data(activityLocationMapper.deleteByIds(idList));
    }

    @PostMapping("insertOrUpdateActivityLocation")
    public SaResult insertOrUpdateActivityLocation(
            @RequestBody ActivityLocation activityLocation) {
        log.info("insertOrUpdateActivityLocation: {}", activityLocation);
        /*
         * 只有一个场地管理者，审批人动态分配
         * */
        final LocalDateTime now = LocalDateTime.now();
        if (activityLocation.getId() == null) {
//            activityLocation.setDeleted(DeletedEnum.NORMAL.getCode());
//            activityLocation.setCreateTime(now);
//            activityLocation.setUpdateTime(now);
            /*
             *
             * */
            activityLocation.setApproverId(userMapper.selectUserIdByActivityLocationRole());
//            activityLocation.setApproveTime(null);
//            activityLocation.setApprovalStatus(ApprovalStatus.UNAPPROVED.getCode());
//            activityLocation.setApproveReason(null);
        }
        if (activityLocation.getId() != null) {
            /*
             * 是否更新审批时间
             * */
            if (!Objects.equals(activityLocation.getApprovalStatus(), ApprovalStatusEnum.UNAPPROVED.getCode())) {
                activityLocation.setApproveTime(now);
            }
        }
        log.info("insertOrUpdateActivityLocation end: {}", activityLocation);
        return SaResult.data(activityLocationMapper.insertOrUpdate(activityLocation));
    }


    @GetMapping("selectActivityLocationList")
    public SaResult selectActivityLocationList() {
        log.info("selectActivityLocationList");
        return SaResult.data(activityLocationMapper.selectList(null));
    }
    @GetMapping("selectActivityLocationListWithApproved")
    public SaResult selectActivityLocationListWithApproved() {
        log.info("selectActivityLocationListWithApproved");
        final LambdaQueryWrapper<ActivityLocation> wrapper = new LambdaQueryWrapper<ActivityLocation>().eq(true, ActivityLocation::getApprovalStatus, ApprovalStatusEnum.APPROVED.getCode());
        return SaResult.data(activityLocationMapper.selectList(wrapper));
    }
    @GetMapping("activityLocationWithActivityEndTimeVO")
    public SaResult activityLocationWithActivityEndTimeVO() {
        log.info("activityLocationWithActivityEndTimeVO");
        return SaResult.data(activityLocationMapper.activityLocationWithActivityEndTimeVO());
    }
    /*
    * 当前是场地已审批、一个场地对多个的活动也已审批
    * 才会显示场地被占用
    * todo
    * 问题一：会出现多个未审批的活动申请同一个已审批了的场地，那么其中一个活动审批了，其他活动又该如何呢
    * */
    @GetMapping("activityLocationWithActivityEndTime")
    public SaResult activityLocationWithActivityEndTime() {
        log.info("activityLocationWithActivityEndTime");
        return SaResult.data(activityLocationMapper.activityLocationWithActivityEndTime());
    }
}
