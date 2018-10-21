package com.damon.oauth.domain.user.command;

import com.damon.oauth.domain.user.aggregate.UserId;
import com.damon.shared.tenant.TenantId;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * @author Damon S.
 */
@Getter
@Builder
public class CreateUserCommand {
    private final UserId userId;
    private final String userName;
    private final String password;
    private final String captcha;
    private final TenantId tenantId;
    private final Long createdBy;
    private final LocalDateTime createdAt;
}
