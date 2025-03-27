package com.future.csamsserver.domain;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.future.csamsserver.enumeration.DeletedEnum;
import com.future.csamsserver.enumeration.FileCategoryEnum;
import com.future.csamsserver.enumeration.TargetTypeEnum;
import lombok.Data;

/**
 * 统一文件表
 * @TableName file_oss
 */
@TableName(value ="file_oss")
@Data
public class FileOss implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 关联类型（用户0/社团1/活动2）
     */
    @TableField(value = "target_type")
//    private Integer targetType;
    private TargetTypeEnum targetType;

    /**
     * 关联ID
     */
    @TableField(value = "target_id")
    private Long targetId;

    /**
     * 文件URL
     */
    @TableField(value = "file_url")
    private String fileUrl;

    /**
     * 原始文件名
     */
    @TableField(value = "file_name")
    private String fileName;

    /**
     * 文件类型（如image/jpeg、application/pdf）
     */
    @TableField(value = "file_type")
    private String fileType;

    /**
     * 文件分类（avatar0, image1, attachment2,press_release3）
     */
    @TableField(value = "file_category")
//    private Integer fileCategory;
    private FileCategoryEnum fileCategory;

    /**
     * 文件大小（字节）
     */
    @TableField(value = "file_size")
    private Long fileSize;

    /**
     * 是否删除，1表示已删除，0表示未删除
     */
    @TableLogic
    @TableField(value = "is_deleted")
//    private Integer deleted;
    private DeletedEnum deleted;

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