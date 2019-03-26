package com.damon.oauth.domain.role.event;

import com.damon.oauth.domain.role.aggregate.RoleId;
import lombok.Builder;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.sql.Timestamp;


/***
 * 应用创建完成事件
 * @author Damon S.
 */
@Getter
@Builder
public class RoleCreatedEvent {
    @TargetAggregateIdentifier
    private final RoleId roleId;
    private final String code;
    private final String name;
    private final String tenantId;
    private final String permsJson;
    private final Long createdBy;
    private final Timestamp createdAt;
}
