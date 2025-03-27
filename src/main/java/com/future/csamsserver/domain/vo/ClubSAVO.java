package com.future.csamsserver.domain.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.future.csamsserver.domain.Club;
import lombok.Data;

/**
 * @description
 * @dateTime 2025:03:08 22:04
 * @user Jenming
 */
@Data
public class ClubSAVO extends Club {

    @TableField("founder_name")
    private String founderName;
    @TableField("approver_name")

    private String approverName;
}
