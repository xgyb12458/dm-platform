package com.damon.oauth.domain.permission.command;

import com.damon.shared.tenant.TenantId;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateOperationCommand {
    private final String code;
    private final String name;
    private final Long createdBy;
    private final TenantId tenantId;
}
