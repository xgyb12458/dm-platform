package com.damon.oauth.domain.role.event;

import com.damon.oauth.domain.role.aggregate.RoleId;
import lombok.Builder;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.sql.Timestamp;

/**
 * 应用更新事件
 * @author Damon S.
 */
@Getter
@Builder
public class RoleUpdatedEvent {
    @TargetAggregateIdentifier
    private final RoleId roleId;
    private final String name;
    private final String platform;
    private final String permsJson;
    private final Long updatedBy;
    private final Timestamp updatedAt;
}
