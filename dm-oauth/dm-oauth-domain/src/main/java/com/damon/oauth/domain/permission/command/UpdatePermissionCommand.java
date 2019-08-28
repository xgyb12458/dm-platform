package com.damon.oauth.domain.permission.command;

import lombok.Builder;
import lombok.Value;

/**
 * 创建权限域命令
 * @author Damon
 */
@Value
@Builder
public final class UpdatePermissionCommand {
    private final Long      permissionId;
    private final Long      resourceId;
    private final Long      operationId;
    private final String    platform;
    private final Long      updatedBy;
}
