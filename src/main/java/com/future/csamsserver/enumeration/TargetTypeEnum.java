package com.future.csamsserver.enumeration;

/**
 * @description
 * @dateTime 2025:03:13 00:06
 * @user Jenming
 */
import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum TargetTypeEnum {
    USER(0, "用户"),
    CLUB(1, "社团"),
    ACTIVITY(2, "活动"),
    LOCATION(3, "活动地点");

    @EnumValue  // 标记数据库存储的值
    @JsonValue  // 标记JSON序列化值
    private final int code;
    private final String desc;

    TargetTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    // 根据code获取枚举（用于反序列化）
    public static TargetTypeEnum of(int code) {
        for (TargetTypeEnum value : values()) {
            if (value.code == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("无效的target_type值: " + code);
    }
}