package com.future.csamsserver.domain.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.future.csamsserver.domain.ActivityMember;
import com.future.csamsserver.domain.ClubMember;
import lombok.Data;

/**
 * @description
 * @dateTime 2025:03:08 22:04
 * @user Jenming
 */
@Data
public class ActivityMemberSAVO extends ActivityMember {

    @TableField("user_name")
    private String userName;
    @TableField("activity_name")
    private String activityName;
    @TableField("approver_name")
    private String approverName;
}
