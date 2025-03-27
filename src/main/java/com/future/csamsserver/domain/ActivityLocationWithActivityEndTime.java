package com.future.csamsserver.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class ActivityLocationWithActivityEndTime {

    /**
     * 场地名称
     */
    @TableField(value = "name")
    private String name;
    @TableField("activity_end_time")
    private LocalDateTime activityEndTime;
}