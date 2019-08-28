package com.damon.oauth.domain.operation.command;

import com.damon.shared.tenant.TenantId;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

/**
 * 查询操作域命令
 * @author damon
 */
@Value
@Builder
public class QueryOperationCommand {
    private final Long          operationId;
    private final String        code;
    private final String        name;
    private final String        platform;
    private final TenantId      tenantId;
    private final Long          createdBy;
    private final Long          updatedBy;
    private final LocalDateTime createdFrom;
    private final LocalDateTime updatedFrom;
    private final LocalDateTime createdTo;
    private final LocalDateTime updatedTo;
}
