package com.damon.oauth.domain.permission.event;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

/**
 * @author Damon S.
 * @version v1.0.1
 * @date 2019年04月19日 23:33
 */
@Value
@Builder
public class PermissionCreatedEvent {
    private final Long          permissionId;
    private final Long          resourceId;
    private final Long          operationId;
    private final String        platform;
    private final Long          createdBy;
    private final LocalDateTime createdAt;
}
