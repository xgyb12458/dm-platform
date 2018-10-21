package com.damon.oauth.domain.permission.command;

import com.damon.shared.tenant.TenantId;
import lombok.Builder;
import lombok.Getter;

/**
 * @author Damon
 */
@Getter
@Builder
public final class CreatePermissionCommand {
    private final Long resourceId;
    private final Long operationId;
    private final Long createdBy;
    private final TenantId tenantId;
}
