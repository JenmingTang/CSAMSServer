package com.future.csamsserver.domain.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.future.csamsserver.domain.ActivityLocation;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @description
 * @dateTime 2025:03:08 22:04
 * @user Jenming
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ActivityLocationWithActivityEndTimeVO extends ActivityLocation {

    @TableField("approver_name")
    private String approverName;
    @TableField("activity_end_time")
    private LocalDateTime activityEndTime;
}
