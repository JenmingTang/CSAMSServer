package com.future.csamsserver.enumeration;

/**
 * @description
 * @dateTime 2025:03:10 01:07
 * @user Jenming
 */
public enum ApprovalStatusEnum {
    UNAPPROVED(0, "待审批"),
    APPROVED(1, "审批通过"),
    REJECTED(2, "审批拒绝");

    private final Integer code;
    private final String message;

    ApprovalStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
