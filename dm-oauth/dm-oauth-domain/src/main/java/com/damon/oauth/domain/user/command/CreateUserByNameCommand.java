package com.damon.oauth.domain.user.command;

import com.damon.oauth.domain.user.aggregate.UserId;
import com.damon.oauth.shared.enums.UserType;
import com.damon.shared.tenant.TenantId;
import lombok.Builder;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.time.LocalDateTime;

/**
 * 创建用户命令
 * @author Damon S.
 */
@Value
@Builder
public class CreateUserByNameCommand {
    @TargetAggregateIdentifier
    private final UserId    userId;
    private final String    userName;
    private final String    password;
    private final UserType  type;
    private final TenantId  tenantId;
    private final Long      createdBy;
    private final LocalDateTime createdAt;
}
