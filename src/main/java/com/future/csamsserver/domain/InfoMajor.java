package com.future.csamsserver.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 专业表
 * @TableName info_major
 */
@TableName(value ="info_major")
@Data
public class InfoMajor implements Serializable {
    /**
     * 专业ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 所属部门ID
     */
    @TableField(value = "department_id")
    private Integer departmentId;

    /**
     * 专业名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 专业代码
     */
    @TableField(value = "code")
    private String code;

    /**
     * 专业描述
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