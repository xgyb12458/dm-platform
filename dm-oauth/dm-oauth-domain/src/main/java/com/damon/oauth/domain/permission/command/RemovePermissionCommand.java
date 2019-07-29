package com.damon.oauth.domain.permission.command;

import lombok.RequiredArgsConstructor;
import lombok.Value;

/**
 * 删除操作域
 * @author damon
 */
@Value
@RequiredArgsConstructor
public class RemovePermissionCommand {
    private final Long permissionId;
    private final Long updatedBy;
}
