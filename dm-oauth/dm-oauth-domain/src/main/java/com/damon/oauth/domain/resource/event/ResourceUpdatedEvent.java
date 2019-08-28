package com.damon.oauth.domain.resource.event;

import com.damon.shared.enums.SwitchState;
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
public class ResourceUpdatedEvent {
    private final Long          resourceId;
    private final String        name;
    private final String        path;
    private final Integer       sort;
    private final SwitchState   state;
    private final String        platform;
    private final Long          updatedBy;
    private final LocalDateTime updatedAt;
}
