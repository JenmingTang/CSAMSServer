package com.future.csamsserver.domain.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.future.csamsserver.domain.ActivityLocation;
import com.future.csamsserver.domain.ActivityMember;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @description
 * @dateTime 2025:03:08 22:04
 * @user Jenming
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ActivityLocationVO extends ActivityLocation {

    @TableField("approver_name")
    private String approverName;
}
