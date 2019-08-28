package com.damon.oauth.domain.operation.command;

import com.damon.shared.tenant.TenantId;
import lombok.Builder;
import lombok.Value;

/**
 * 创建操作域命令
 * @author Damon S.
 */
@Value
@Builder
public class CreateOperationCommand {
    private final Long      operationId;
    private final String    code;
    private final String    name;
    private final String    platform;
    private final TenantId  tenantId;
    private final Long      createdBy;
}
