package com.damon.oauth.domain.resource.event;

import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.time.LocalDateTime;

/**
 * 删除操作域
 * @author damon
 */
@Value
@RequiredArgsConstructor
public class ResourceRemovedEvent {
    private final Long          resourceId;
    private final Long          updatedBy;
    private final LocalDateTime updatedAt;
}
