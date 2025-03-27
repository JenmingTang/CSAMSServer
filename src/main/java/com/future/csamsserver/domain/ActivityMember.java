package com.future.csamsserver.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 活动成员表
 * @TableName activity_member
 */
@TableName(value ="activity_member")
@Data
public class ActivityMember implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户ID
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 活动ID
     */
    @TableField(value = "activity_id")
    private Long activityId;

    /**
     * 加入时间
     */
    @TableField(value = "join_time")
    private LocalDateTime joinTime;

    /**
     * 审批状态（0: 待审批, 1: 审批通过, 2: 审批拒绝）
     */
    @TableField(value = "approval_status")
    private Integer approvalStatus;

    /**
     * 审批人ID，对应用户id
     */
    @TableField(value = "approver_id")
    private Long approverId;

    /**
     * 审批时间
     */
    @TableField(value = "approve_time")
    private LocalDateTime approveTime;

    /**
     * 审批意见
     */
    @TableField(value = "approve_reason")
    private String approveReason;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private LocalDateTime updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}