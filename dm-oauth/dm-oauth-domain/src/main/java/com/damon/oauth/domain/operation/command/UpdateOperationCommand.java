package com.damon.oauth.domain.operation.command;

import lombok.Builder;
import lombok.Value;

/**
 * 更新操作域
 * @author damon
 */
@Value
@Builder
public class UpdateOperationCommand {
    private final Long      operationId;
    private final String    name;
    private final String    platform;
    private final Long      updatedBy;
}
