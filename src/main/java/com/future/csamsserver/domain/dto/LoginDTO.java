package com.future.csamsserver.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @description
 * @dateTime 2025:03:04 22:23
 * @user Jenming
 */
@Data

public class LoginDTO {
        private String username;
        private String password;
        /*
        *
而 JSON 序列化库（如 Jackson）默认会将字段名解析为 rememberMe，而非 isRememberMe。
*
    private boolean rememberMe; // 移除 "is" 前缀
    * 或
        * */

        @JsonProperty("isRememberMe") // 强制指定 JSON 字段名
        private boolean isRememberMe;
}
