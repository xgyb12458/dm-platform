package com.damon.oauth.domain.audit.aggregate;

import lombok.Builder;
import lombok.Value;

/**
 * 审计日志
 * @author Damon S.
 * @version v1.0.1
 * @date 2019年06月09日 18:34
 */
@Value
@Builder
public class AuditLog {
    private final String  userId;
    private final String  operate;
    private final Long    duration;
    private final String  ip;
    private final String  params;
    private final String  uri;
    private final String  type;
}
