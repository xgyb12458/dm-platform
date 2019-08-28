package com.damon.oauth.domain.resource.event;

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
public class ResourceCreatedEvent {
    private final Long          resourceId;
    private final String        code;
    private final String        name;
    private final String        path;
    private final Integer       sort;
    private final String        platform;
    private final Long          parentId;
    private final Long          createdBy;
    private final LocalDateTime createdAt;
}
