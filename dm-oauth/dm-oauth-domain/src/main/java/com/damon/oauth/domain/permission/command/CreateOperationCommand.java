package com.damon.oauth.domain.permission.command;

import lombok.Builder;
import lombok.Value;

/**
 * 创建操作域命令
 * @author Damon S.
 */
@Value
@Builder
public class CreateOperationCommand {
    private final String code;
    private final String name;
    private final Long createdBy;
}
