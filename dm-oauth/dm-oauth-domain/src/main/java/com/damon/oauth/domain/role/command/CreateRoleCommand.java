package com.damon.oauth.domain.role.command;

import com.damon.oauth.domain.role.aggregate.RoleId;
import lombok.Builder;
import lombok.Getter;

/**
 * 创建角色命令
 * @author Damon S.
 */
@Getter
@Builder
public class CreateRoleCommand {
    private final RoleId roleId;
    private final String code;
    private final String name;
    private final String platform;
    private final String permsJson;
    private final String tenantId;
    private final Long createdBy;
}
