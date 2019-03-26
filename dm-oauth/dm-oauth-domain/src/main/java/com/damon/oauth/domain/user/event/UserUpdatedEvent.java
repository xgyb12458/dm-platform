package com.damon.oauth.domain.user.event;

import com.damon.oauth.domain.user.aggregate.UserId;
import com.damon.shared.tenant.TenantId;
import lombok.Builder;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.time.LocalDateTime;

/**
 * 应用更新事件
 * @author Damon S.
 */
@Getter
@Builder
public class UserUpdatedEvent {
    @TargetAggregateIdentifier
    private final UserId            userId;
    private final String            nickName;
    private final String            rolesJson;
    private final String            phoneNo;
    private final String            email;
    private final TenantId          tenantId;
    private final Long              updatedBy;
    private final LocalDateTime     updatedAt;

}
