package com.damon.oauth.domain.operation.command;

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
    private final Integer   sort;
    private final Integer   platform;
    private final Long      tenantId;
    private final Long      createdBy;
}
