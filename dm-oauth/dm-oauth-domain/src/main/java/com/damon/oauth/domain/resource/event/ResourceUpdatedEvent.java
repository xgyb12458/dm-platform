package com.damon.oauth.domain.resource.event;

import com.damon.oauth.domain.resource.aggregate.ResourceId;
import lombok.Builder;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

/**
 * @author Damon S.
 * @version v1.0.1
 * @date 2019年04月19日 23:33
 */
@Value
@Builder
public class ResourceUpdatedEvent {
    @TargetAggregateIdentifier
    private final ResourceId    resourceId;
    private final String        code;
    private final String        name;
    private final Long          updatedBy;
}
