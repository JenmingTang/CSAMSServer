-- 活动场地管理
CREATE TABLE activity_location
(
    id              INT UNSIGNED     NOT NULL AUTO_INCREMENT COMMENT '班级ID',
    name            VARCHAR(100)     NOT NULL COMMENT '场地名称',
    description     TEXT COMMENT '场地描述',
    approval_status TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '审批状态（0: 待审批, 1: 审批通过, 2: 审批拒绝）',
    approver_id     BIGINT UNSIGNED           COMMENT '审批人ID，对应用户id',
    approve_time    DATETIME                  DEFAULT NULL COMMENT '审批时间',
    approve_reason  VARCHAR(255)              DEFAULT '' COMMENT '审批意见',
    is_deleted      TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除，1表示已删除，0表示未删除',
    create_time     DATETIME                  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time     DATETIME                  DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY pk_id (id),
    INDEX idx_approval_status (approval_status)
    -- UNIQUE INDEX uk_name (name),
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='活动场地表';
insert into role (role_name, description)
values ('ACTIVITY_LOCATION', '活动场地管理者');
insert into permission (perm_code, description)
values ('BUTTON_SEARCH_ACTIVITY_LOCATION', '搜索活动场地按钮'),
       ('BUTTON_ADD_ACTIVITY_LOCATION', '添加活动场地按钮'),
       ('BUTTON_BATCH_DELETE_ACTIVITY_LOCATION', '批量删除活动场地按钮'),
       ('BUTTON_EDIT_ACTIVITY_LOCATION', '编辑活动场地按钮'),
       ('BUTTON_DELETE_ACTIVITY_LOCATION', '删除活动场地按钮'),
       ('BUTTON_EXCEL_ACTIVITY_LOCATION', '导出活动场地按钮'),
       ('BUTTON_EXCEL_ACTIVITY_LOCATION_ALL', '导出全部活动场地按钮');