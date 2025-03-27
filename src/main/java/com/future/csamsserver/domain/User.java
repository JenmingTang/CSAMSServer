package com.future.csamsserver.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.future.csamsserver.serializer.EmailDesensitizeSerializer;
import com.future.csamsserver.serializer.PhoneDesensitizeSerializer;
import lombok.Data;

/**
 * 用户表
 * @TableName user
 */
@TableName(value ="user")
@Data
public class User implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户名（学号/工号）
     */
    @TableField(value = "username")
    private String username;

    /**
     * 加密密码
     */
    @TableField(value = "password")
    private String password;

    /**
     * 真实姓名
     */
    @TableField(value = "real_name")
    private String realName;

    /**
     * 学院
     */
    @TableField(value = "college")
    private String college;

    /**
     * 专业
     */
    @TableField(value = "major")
    private String major;

    /**
     * 班级
     */
    @TableField(value = "user_class")
    private String userClass;

    /**
     * 邮箱
     */
    @TableField(value = "email")
//    @JsonSerialize(using = EmailDesensitizeSerializer.class)
    private String email;

    /**
     * 手机号
     */
    @TableField(value = "phone")
//    @JsonSerialize(using = PhoneDesensitizeSerializer.class)
    private String phone;

    /**
     * 头像OSS路径
     */
    @TableField(value = "avatar_url")
    private String avatarUrl;

    /**
     * 是否启用（1:已启用, 0:未启用）
     */
    @TableField(value = "is_enabled")
    private Integer enabled;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
//    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
//    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}