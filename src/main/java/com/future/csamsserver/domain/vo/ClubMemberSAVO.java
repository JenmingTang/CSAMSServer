package com.future.csamsserver.domain.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.future.csamsserver.domain.ClubMember;
import lombok.Data;

/**
 * @description
 * @dateTime 2025:03:08 22:04
 * @user Jenming
 */
@Data
public class ClubMemberSAVO extends ClubMember {

    @TableField("user_name")
    private String userName;
    @TableField("club_name")
    private String clubName;
    @TableField("approver_name")
    private String approverName;
}
