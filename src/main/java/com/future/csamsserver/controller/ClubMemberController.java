package com.future.csamsserver.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;

import com.future.csamsserver.domain.ClubMember;
import com.future.csamsserver.enumeration.ApprovalStatusEnum;
import com.future.csamsserver.mapper.ClubMapper;
import com.future.csamsserver.mapper.ClubMemberMapper;
import com.future.csamsserver.utils.U;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * 社团成员表(ClubMember)表控制层
 *
 * @author Jenming
 * @since 2025-03-08 22:03:53
 */
@Slf4j
@RestController
@RequestMapping("clubMember")
public class ClubMemberController {

    private final ClubMemberMapper clubMemberMapper;
    private final ClubMapper clubMapper;

    public ClubMemberController(ClubMemberMapper clubMemberMapper, ClubMapper clubMapper) {
        this.clubMemberMapper = clubMemberMapper;
        this.clubMapper = clubMapper;
    }

    @GetMapping("selectClubMemberListByParams")
    public SaResult selectClubMemberListByParams(
            @RequestParam(defaultValue = "1") Long current,
            @RequestParam(defaultValue = "10") Long size,
            @RequestParam(required = false) LocalDateTime createTimeStart,
            @RequestParam(required = false) LocalDateTime createTimeEnd,
            @RequestParam(required = false) String userName,
            @RequestParam(required = false) String clubName,
            @RequestParam(required = false) String approverName,
            @RequestParam(required = false) Integer approvalStatus

    ) {
        log.info("selectClubMemberListByParams: current={}, size={}, createTimeStart={}, createTimeEnd={}, userName={}, clubName={}, approverName={}, approvalStatus={} "
                , current, size, createTimeStart, createTimeEnd,userName, clubName, approverName, approvalStatus);
        if (current != null && current == 1) {
            current = 0L;
        }
        return SaResult.data(U.wrapAsSAResult(current + 1, size, clubMemberMapper.selectCount(null),
                clubMemberMapper.selectClubMemberListByParams(current, size, createTimeStart, createTimeEnd, approvalStatus, userName, clubName, approverName)));
    }

    @DeleteMapping("deleteClubMemberById/{id}")
    public SaResult deleteClubMemberById(
            @PathVariable("id") Long id) {
        log.info("deleteClubMemberById: {}", id);
        return SaResult.data(clubMemberMapper.deleteById(id));
    }

    @DeleteMapping("deleteClubMemberListByIdList")
    public SaResult deleteClubMemberListByIdList(
            @RequestBody List<Long> idList) {
        log.info("deleteClubMemberListByIdList: {}", idList);
        return SaResult.data(clubMemberMapper.deleteByIds(idList));
    }

    @PostMapping("insertOrUpdateClubMember")
    public SaResult insertOrUpdateClubMember(
            @RequestBody ClubMember clubMember) {
        log.info("insertOrUpdateClubMember: {}", clubMember);
        /*
         * 新建条目初始化
         * */
        if (clubMember.getId() == null) {
            clubMember.setApprovalStatus(ApprovalStatusEnum.UNAPPROVED.getCode());
            /*
             * 成员默认审批人为社团创始人
             * */
            clubMember.setApproverId(clubMapper.selectById(clubMember.getClubId()).getFounderId());
            /*
             * 申请加入者即为申请人
             * */
            clubMember.setUserId(StpUtil.getLoginIdAsLong());
        }
        /*
         * 审批时间随更新而改变
         * */

        if (!Objects.equals(clubMember.getApprovalStatus(), ApprovalStatusEnum.UNAPPROVED.getCode())){
            clubMember.setApproveTime(LocalDateTime.now());
        }
        /*
         * 审批通过修改加入时间
         * */
        if (Objects.equals(clubMember.getApprovalStatus(), ApprovalStatusEnum.APPROVED.getCode())) {
            clubMember.setJoinTime(LocalDateTime.now());
        }
        log.info("insertOrUpdateClubMember end: {}", clubMember);
        /*
        * ClubMember(id=null, userId=1, clubId=4, joinTime=null, approvalStatus=0, approverId=1, approveTime=null, approveReason=, createTime=null, updateTime=null)
        * */
        return SaResult.data(clubMemberMapper.insertOrUpdate(clubMember));
    }


    @GetMapping("selectClubMemberList")
    public SaResult selectClubMemberList() {
        log.info("selectClubMemberList");
        return SaResult.data(clubMemberMapper.selectList(null));
    }
}
