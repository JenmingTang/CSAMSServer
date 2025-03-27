package com.future.csamsserver.exception;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.NotRoleException;
import cn.dev33.satoken.util.SaResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @description
 * @dateTime 2025:03:06 16:13
 * @user Jenming
 */
@RestControllerAdvice
@Slf4j
public class SaTokenExceptionHandler {
    @ExceptionHandler(NotLoginException.class)
    public SaResult NotLoginException(NotLoginException e) {
        log.info("NotLoginException",e);
        return SaResult.error( "请登录！");
    }

    // 捕获角色异常
    @ExceptionHandler(NotRoleException.class)
    public SaResult handleNotRole(NotRoleException e) {
        return SaResult.error( "非法访问：所需角色 " + e.getRole());
    }

    // 捕获权限异常（按需添加）
    @ExceptionHandler(NotPermissionException.class)
    public SaResult handleNotPermission(NotPermissionException e) {
        return SaResult.error( "无权限：" + e.getPermission());
    }
}