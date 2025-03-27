package com.future.csamsserver.domain.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.util.List;

/**
 * @description
 * @dateTime 2025:03:07 17:29
 * @user Jenming
 */
@Data
public class RolePermissionUpdateDTO {
    @NotNull(message = "角色ID不能为空")
    private Long roleId;

    @NotEmpty(message = "权限列表不能为空")
    private List<@Positive Long> permissionIds;
}
