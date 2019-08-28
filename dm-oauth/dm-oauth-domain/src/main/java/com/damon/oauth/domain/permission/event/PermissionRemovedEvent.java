package com.damon.oauth.domain.permission.event;

import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.time.LocalDateTime;

/**
 * 删除操作域
 * @author damon
 */
@Value
@RequiredArgsConstructor
public class PermissionRemovedEvent {
    private final Long          permissionId;
    private final Long          updatedBy;
    private final LocalDateTime updatedAt;
}
