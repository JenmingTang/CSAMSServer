package com.future.csamsserver.enumeration;

import lombok.Getter;

/**
 * @description
 * @dateTime 2025:05:10 23:06
 * @user Jenming
 */
@Getter
public enum StatisticsEnum {
    VIEW_COUNT("view_count", "访问量",0L),
    PARTICIPATION_COUNT("participation_count", "活动人次",0L),
    CLUB_COUNT("club_count", "社团数量",0L),
    ACTIVITY_COUNT("activity_count", "活动数量",0L),
    USER_COUNT("user_count", "用户数量",0L),


    ROLE_COUNT("role_count", "角色数量",0L),
    PERMISSION_COUNT("permission_count", "权限数量",0L),
    ;

    private final String type;
    private final String name;
//    无符号整数对象
    private final long value;

    StatisticsEnum(String type, String name,long value) {
        this.type = type;
        this.name = name;
        this.value = value;
    }

}
