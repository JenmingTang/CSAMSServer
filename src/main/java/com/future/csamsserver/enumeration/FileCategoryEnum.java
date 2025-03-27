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
public enum FileCategoryEnum {
    AVATAR(0, "头像"),
    IMAGE(1, "图片"),
    ATTACHMENT(2, "附件"),
    PRESS_RELEASE(3, "新闻稿"),
    SHOW_PRESS_RELEASE(4, "展示新闻稿");

    @EnumValue
    @JsonValue
    private final int code;
    private final String desc;

    FileCategoryEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static FileCategoryEnum of(int code) {
        for (FileCategoryEnum value : values()) {
            if (value.code == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("无效的file_category值: " + code);
    }
}