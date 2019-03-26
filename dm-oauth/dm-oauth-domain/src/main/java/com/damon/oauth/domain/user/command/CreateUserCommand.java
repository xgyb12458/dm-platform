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
public class CreateUserCommand {
    @TargetAggregateIdentifier
    private final UserId        userId;
    private final String        userName;
    private final String        nickName;
    private final String        password;
    private final String        phoneNo;
    private final String        captcha;
    private final String        email;
    private final UserType      type;
    private final TenantId      tenantId;
    private final Long          createdBy;
}
