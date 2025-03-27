package com.future.csamsserver.domain.dto;

import lombok.Data;

/**
 * @description
 * @dateTime 2025:03:11 20:31
 * @user Jenming
 */
@Data
public class ResetPasswordDTO {
    String email;
    String code;
    String password;
    String confirmPassword;
}
