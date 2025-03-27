package com.future.csamsserver.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

/**
 * 活动场地表
 * @TableName activity_location
 */
@TableName(value ="activity_location")
@Data
public class ActivityLocation implements Serializable {
    /**
     * 班级ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 场地名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 场地描述
     */
    @TableField(value = "description")
    private String description;

    /**
     * 审批状态（0: 待审批, 1: 审批通过, 2: 审批拒绝）
     private ApprovalStatus approvalStatus;
     * todo 1 插入条目：验证mapper.xml是否可行，而不是Java编程
     *
     * todo 2 插入条目：验证自带方法插入是否可行
     "approvalStatus": "UNAPPROVED",
     "approvalStatus": 0,
     都报SQL语法错误，都被web解析的SQL为'UNAPPROVED'
     实际Java中为枚举对象approvalStatus=UNAPPROVED
     *
     *
     */
    /*
    * 方案二 自定义类型处理器
    * @MappedTypes(ApprovalStatus.class)
public class ApprovalStatusTypeHandler extends BaseTypeHandler<ApprovalStatus> {
    @Override
    public void setNonNullParameter(...) {
        ps.setInt(i, parameter.getCode());
    }
    // 其他重写方法实现code与枚举的转换
}
* 字段
* @TableField(value = "approval_status", typeHandler = ApprovalStatusTypeHandler.class)
private ApprovalStatus approvalStatus;
xml
* * <result column="approval_status" property="approvalStatus"
        javaType="com.future.csamsserver.enumeration.ApprovalStatus"/>

    * */
    @TableField(value = "approval_status")
//    private ApprovalStatus approvalStatus;
    private Integer approvalStatus;

    /**
     * 审批人ID，对应用户id
     */
    @TableField(value = "approver_id")
    private Long approverId;

    /**
     * 审批时间
     */
    @TableField(value = "approve_time")
    private LocalDateTime approveTime;

    /**
     * 审批意见
     */
    @TableField(value = "approve_reason")
    private String approveReason;

    /**
     * 是否删除，1表示已删除，0表示未删除
     */
    @TableField(value = "is_deleted")
    private Integer deleted;

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