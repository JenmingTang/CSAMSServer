package com.future.csamsserver.enumeration;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * @description
 * @dateTime 2025:03:13 00:07
 * @user Jenming
 */
@Getter
public enum DeletedEnum {
    NORMAL(0, "未删除"),
    DELETED(1, "已删除");

    @EnumValue
    @JsonValue
    private final int code;
    private final String desc;

    DeletedEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static DeletedEnum of(int code) {
        for (DeletedEnum value : values()) {
            if (value.code == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("无效的is_deleted值: " + code);
    }
}