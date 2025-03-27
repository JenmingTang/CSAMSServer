package com.future.csamsserver.test;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.future.csamsserver.serializer.EmailDesensitizeSerializer;
import com.future.csamsserver.serializer.PhoneDesensitizeSerializer;
import lombok.Data;

/**
 * @description
 * @dateTime 2025:03:08 19:19
 * @user Jenming
 */
@Data
public class UserInfo {
    @JsonSerialize(using = PhoneDesensitizeSerializer.class)
    private String mobile;

    @JsonSerialize(using = EmailDesensitizeSerializer.class)
    private String email;

}
