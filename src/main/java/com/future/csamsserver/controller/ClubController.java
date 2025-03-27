package com.future.csamsserver.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.future.csamsserver.domain.Club;
import com.future.csamsserver.enumeration.ApprovalStatusEnum;
import com.future.csamsserver.mapper.ClubMapper;
import com.future.csamsserver.mapper.UserMapper;
import com.future.csamsserver.utils.U;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * 社团表(Club)表控制层
 *
 * @author Jenming
 * @since 2025-03-08 22:03:53
 */
@Slf4j
@RestController
@RequestMapping("club")
public class ClubController {

    private final ClubMapper clubMapper;
    private final UserMapper userMapper;

    public ClubController(ClubMapper clubMapper, UserMapper userMapper) {
        this.clubMapper = clubMapper;
        this.userMapper = userMapper;
    }

    @GetMapping("selectClubListByParams")
    public SaResult selectClubListByParams(
            @RequestParam(defaultValue = "1") Long current,
            @RequestParam(defaultValue = "10") Long size,
            @RequestParam(required = false) LocalDateTime createTimeStart,
            @RequestParam(required = false) LocalDateTime createTimeEnd,
            @RequestParam(required = false) String name ,
            @RequestParam(required = false) Integer approvalStatus,
            @RequestParam(required = false) String approverName
    ) {
        log.info("selectClubListByParams: current={}, size={}, createTimeStart={}, createTimeEnd={}", current, size, createTimeStart, createTimeEnd);
        if (current != null && current == 1) {
            current = 0L;
        }

        return SaResult.data(U.wrapAsSAResult(current + 1, size, clubMapper.selectCount(null),
                clubMapper.selectClubListByParams(current, size, createTimeStart, createTimeEnd,name,approvalStatus,approverName)));
    }

    @DeleteMapping("deleteClubById/{id}")
    public SaResult deleteClubById(
            @PathVariable("id") Long id) {
        log.info("deleteClubById: {}", id);
        return SaResult.data(clubMapper.deleteById(id));
    }

    @DeleteMapping("deleteClubListByIdList")
    public SaResult deleteClubListByIdList(
            @RequestBody List<Long> idList) {
        log.info("deleteClubListByIdList: {}", idList);
        return SaResult.data(clubMapper.deleteByIds(idList));
    }

    @PostMapping("insertOrUpdateClub")
    public SaResult insertOrUpdateClub(
            @RequestBody Club club) {
        log.info("insertOrUpdateClub: {}", club);
        /*
        * 创建者及创建者
        * 审批状态归零
        * */
        if (club.getId() == null){
            club.setFounderId(StpUtil.getLoginIdAsLong());
            club.setApprovalStatus(ApprovalStatusEnum.UNAPPROVED.getCode());
            /*
             * tang todo
             * 默认审批人为唯一超管
             * 必须保证数据库存在一位超管，不然列表报错
             * */
            club.setApproverId(userMapper.selectUserListWithSuperAdminRole().get(0).getId());
        }
        /*
        * 更新审批时间
        * */
        if (!Objects.equals(club.getApprovalStatus(), ApprovalStatusEnum.UNAPPROVED.getCode())){
            club.setApproveTime(LocalDateTime.now());
        }
        return SaResult.data(clubMapper.insertOrUpdate(club));
    }


    @GetMapping("selectClubList")
    public SaResult selectClubList() {
        log.info("selectClubList");
        return SaResult.data(clubMapper.selectList(null));
    }
    @GetMapping("selectClubListWithApproved")
    public SaResult selectClubListWithApproved() {
        log.info("selectClubListWithApproved");
        final LambdaQueryWrapper<Club> clubLambdaQueryWrapper = new LambdaQueryWrapper<Club>().eq(Club::getApprovalStatus, ApprovalStatusEnum.APPROVED.getCode());
        return SaResult.data(clubMapper.selectList(clubLambdaQueryWrapper));
    }
}
