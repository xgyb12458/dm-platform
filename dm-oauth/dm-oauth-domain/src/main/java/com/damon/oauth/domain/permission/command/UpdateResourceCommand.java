package com.damon.oauth.domain.permission.command;

import lombok.Builder;
import lombok.Value;

/**
 * 更新操作域
 * @author damon
 */
@Value
@Builder
public class UpdateResourceCommand {
    private final Long resourceId;
    private final String name;
    private final Long updatedBy;
}
