package com.damon.oauth.domain.resource.event;

import lombok.Builder;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.time.LocalDateTime;

/**
 * @author Damon S.
 * @version v1.0.1
 * @date 2019年04月19日 23:33
 */
@Value
@Builder
public class ResourceUpdatedEvent {
    @TargetAggregateIdentifier
    private final Long      resourceId;
//    private final String    code;
    private final String    name;
    private final String    platform;
//    private final Long      parentId;
    private final Long      updatedBy;
    private final LocalDateTime updatedAt;
}
