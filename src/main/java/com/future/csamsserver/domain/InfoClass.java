package com.future.csamsserver.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 班级表
 * @TableName info_class
 */
@TableName(value ="info_class")
@Data
public class InfoClass implements Serializable {
    /**
     * 班级ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 所属专业ID
     */
    @TableField(value = "major_id")
    private Integer majorId;

    /**
     * 班级名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 班级代码
     */
    @TableField(value = "code")
    private String code;

    /**
     * 班级描述
     */
    @TableField(value = "description")
    private String description;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private LocalDateTime updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}