package com.damon.oauth.domain.user.command;

import com.damon.oauth.domain.user.aggregate.UserId;
import com.damon.oauth.shared.enums.UserType;
import com.damon.shared.tenant.TenantId;
import lombok.Builder;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

/**
 * 创建用户命令
 * @author Damon S.
 */
@Getter
@Builder
public class CreateUserByWechatCommand {
    @TargetAggregateIdentifier
    private final UserId        userId;
    private final String        userName;
    private final String        password;
    private final String        phone;
    private final String        email;
    private final UserType      type;
    private final TenantId      tenantId;
    private final Long          createdBy;
}
