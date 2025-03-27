package com.future.csamsserver.controller;

import cn.dev33.satoken.util.SaResult;
import com.future.csamsserver.domain.dto.UserRoleUpdateDTO;
import com.future.csamsserver.service.UserRoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description
 * @dateTime 2025:03:06 19:21
 * @user Jenming
 */

// 2. Controller 层 --------------------------------------------
@RestController
@RequestMapping("/user-role")
@RequiredArgsConstructor
public class UserRoleController {

    private final UserRoleService userRoleService;

    @PostMapping("/update")
    public SaResult updateUserRoles(
            @Valid @RequestBody UserRoleUpdateDTO dto) {
        userRoleService.updateUserRoles(dto);
        return SaResult.ok();
    }
}
