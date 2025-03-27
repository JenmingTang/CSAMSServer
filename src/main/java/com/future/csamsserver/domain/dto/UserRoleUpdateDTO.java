package com.future.csamsserver.domain.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

/**
 * @description
 * @dateTime 2025:03:06 19:20
 * @user Jenming
 */
// 1. DTO 定义 -------------------------------------------------
@Data
public class UserRoleUpdateDTO {
    @NotNull(message = "用户ID不能为空")
    private Long userId;

    @NotEmpty(message = "角色列表不能为空")
    private List<Long> roleIds;
}
