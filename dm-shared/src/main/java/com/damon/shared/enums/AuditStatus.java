package com.damon.shared.enums;

/**
 * 审批状态
 * 这是描述一个过程中的某阶段（phase）
 * @author Damon S.
 */
public enum AuditStatus {
    /***审核中。已驳回。已通过。10个字符以内*/
    AUDITING,
    REJECTED,
    APPROVED;
}
