package com.damon.oauth.domain.user.event;

import com.damon.oauth.domain.user.aggregate.UserId;
import com.damon.order.shared.enums.OrderState;
import com.damon.order.shared.enums.OrderType;
import com.damon.shared.tenant.TenantId;
import lombok.Builder;
import lombok.Getter;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

import java.time.LocalDateTime;


/***
 * 应用创建完成事件
 * @author Damon S.
 */
@Getter
@Builder
public class UserCreatedEvent {
    @TargetAggregateIdentifier
    private final UserId userId;
    private final String userName;
    private final String password;
    private final String salt;
    private final OrderType type;
    private final OrderState state;
    private final TenantId tenantId;
    private final Long createdBy;
    private final LocalDateTime createdAt;

}
