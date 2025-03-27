package com.future.csamsserver.domain.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @description
 * @dateTime 2025:03:24 00:11
 * @user Jenming
 */

@TableName(value ="activity")
@Data
public class ActivityTimeConflictEntity {
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
}
