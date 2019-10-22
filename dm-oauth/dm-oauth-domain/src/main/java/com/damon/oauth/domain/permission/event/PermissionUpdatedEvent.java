package com.damon.oauth.domain.permission.event;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

/**
 * 许可域更新成功事件
 * @author Damon S.
 * @version v1.0.1
 * @date 2019年04月19日 23:33
 */
@Value
@Builder
public class PermissionUpdatedEvent {
    private final Long          permissionId;
    private final Long          resourceId;
    private final Long          operationId;
    private final String        platform;
    private final Long          updatedBy;
    private final LocalDateTime updatedAt;
}
