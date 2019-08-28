package com.damon.oauth.domain.permission.command;

import com.damon.shared.tenant.TenantId;
import lombok.Builder;
import lombok.Getter;

/**
 * 创建租户
 * @author damon S.
 */
@Getter
@Builder
public class CreateTenantCommand {
    private final TenantId  tenantId;
    private final String    code;
    private final String    name;
    private final Long      createdBy;
}
