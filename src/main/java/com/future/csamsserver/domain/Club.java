package com.future.csamsserver.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 社团表
 * @TableName club
 */
@TableName(value ="club")
@Data
public class Club implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 社团名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 社团简介
     */
    @TableField(value = "description")
    private String description;

    /**
     * 创始人ID
     */
    @TableField(value = "founder_id")
    private Long founderId;

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