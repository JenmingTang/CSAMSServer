package com.future.csamsserver.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 活动表
 * @TableName activity
 */
@TableName(value ="activity")
@Data
public class Activity implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 活动标题
     */
    @TableField(value = "title")
    private String title;

    /**
     * 活动详情
     */
    @TableField(value = "content")
    private String content;

    /**
     * 活动地点
     */
    @TableField(value = "location")
    private String location;

    /**
     * 开始时间
     */
    @TableField(value = "start_time")
    private LocalDateTime startTime;

    /**
     * 结束时间
     */
    @TableField(value = "end_time")
    private LocalDateTime endTime;

    /**
     * 社团ID
     */
    @TableField(value = "club_id")
    private Long clubId;

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