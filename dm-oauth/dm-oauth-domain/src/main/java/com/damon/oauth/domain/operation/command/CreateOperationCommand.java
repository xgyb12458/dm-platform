package com.damon.oauth.domain.operation.command;

import com.damon.shared.tenant.TenantId;
import lombok.Builder;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

/**
 * 创建操作域命令
 * @author Damon S.
 */
@Value
@Builder
public class CreateOperationCommand {
    @TargetAggregateIdentifier
    private final Long      operationId;
    private final String    code;
    private final String    name;
    private final String    platform;
    private final Long      parentId;
    private final TenantId  tenantId;
    private final Long      createdBy;
}
