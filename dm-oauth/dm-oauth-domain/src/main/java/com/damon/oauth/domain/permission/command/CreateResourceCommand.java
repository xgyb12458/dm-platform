package com.damon.oauth.domain.permission.command;

import lombok.Builder;
import lombok.Value;

/**
 * 创建资源域命令
 * @author damon
 */
@Value
@Builder
public class CreateResourceCommand {
    private final String code;
    private final String name;
    private final Long createdBy;
}
