package com.damon.oauth.domain.operation.event;

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
public class OperationRemovedEvent {
    private final Long          operationId;
    private final Long          updatedBy;
    private final LocalDateTime updatedAt;
}
