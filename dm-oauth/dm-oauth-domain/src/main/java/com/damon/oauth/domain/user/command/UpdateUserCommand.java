package com.damon.oauth.domain.user.command;

import com.damon.oauth.domain.user.aggregate.UserId;
import com.damon.shared.tenant.TenantId;
import lombok.Builder;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

/**
 * 应用更新命令
 * @author Damon S.
 */
@Getter
@Builder
public class UpdateUserCommand {
    @TargetAggregateIdentifier
    private final UserId    userId;
    private final String    nickName;
    private final String    rolesJson;
    private final String    phoneNo;
    private final String    email;
    private final TenantId  tenantId;
    private final Long      updatedBy;
}
