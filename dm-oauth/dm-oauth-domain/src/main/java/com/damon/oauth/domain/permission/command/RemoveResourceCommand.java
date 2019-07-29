package com.damon.oauth.domain.permission.command;

import lombok.RequiredArgsConstructor;
import lombok.Value;

/**
 * 删除操作域
 * @author damon
 */
@Value
@RequiredArgsConstructor
public class RemoveResourceCommand {
    private final Long resourceId;
    private final Long updatedBy;
}
