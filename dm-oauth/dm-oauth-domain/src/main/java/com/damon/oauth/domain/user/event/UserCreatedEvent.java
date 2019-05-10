package com.damon.oauth.domain.user.event;

import com.damon.oauth.domain.user.aggregate.UserId;
import com.damon.oauth.shared.enums.UserState;
import com.damon.oauth.shared.enums.UserType;
import com.damon.shared.tenant.TenantId;
import lombok.Builder;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.time.LocalDateTime;


/***
 * 应用创建完成事件
 * @author Damon S.
 */
@Value
@Builder
public class UserCreatedEvent {
    @TargetAggregateIdentifier
    private final UserId        userId;
    private final String        userName;
    private final String        nickName;
    private final String        password;
    private final String        phoneNo;
    private final String        email;
    private final UserType      type;
    private final UserState     state;
    private final String        salt;
    private final TenantId      tenantId;
    private final Long          createdBy;
    private final LocalDateTime createdAt;
}
