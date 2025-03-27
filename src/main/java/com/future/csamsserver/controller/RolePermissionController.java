package com.future.csamsserver.controller;

import cn.dev33.satoken.util.SaResult;
import com.future.csamsserver.domain.dto.RolePermissionUpdateDTO;
import com.future.csamsserver.domain.dto.UserRoleUpdateDTO;
import com.future.csamsserver.service.PermissionService;
import com.future.csamsserver.service.RolePermissionService;
import com.future.csamsserver.service.UserRoleService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description
 * @dateTime 2025:03:06 19:21
 * @user Jenming
 */

@RestController
@RequestMapping("/role-permission")
@RequiredArgsConstructor
public class RolePermissionController { // 注意保持类名与文件名一致
    private final RolePermissionService rolePermissionService; // 需要对应的Service组件

    @PostMapping("/update")
    public SaResult updateRolePermissions(
            @Valid @RequestBody RolePermissionUpdateDTO dto) {
        rolePermissionService.updateRolePermissions(dto.getRoleId(), dto.getPermissionIds());
        return SaResult.ok().setMsg("权限更新成功");
    }

//    @PostMapping("/{roleId}/permissions")
//    public SaResult updatePermissions(
//            @PathVariable Long roleId,
//            @Valid @RequestBody List<Long> permissionIds) {
//        // ...
//    }

//    @PostMapping("/batch-update")
//    @Operation(summary = "更新角色权限")
////    @PreAuthorize("@pms.has('sys:role:edit')") // 添加权限校验
//    public SaResult updateRolePermissions(
//            @Valid @RequestBody RolePermissionUpdateDTO dto) {
//        try {
//            permissionService.updateRolePermissions(dto);
//            return SaResult.ok("权限更新成功");
//        } catch (ServiceException e) {
//            return SaResult.error(e.getMessage());
//        }
//    }


}
