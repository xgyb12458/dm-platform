package com.damon.oauth.domain.role.command;

import com.damon.oauth.domain.role.aggregate.RoleId;
import com.damon.shared.tenant.TenantId;
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
    private final String permissions;
    private final TenantId tenantId;
    private final Long createdBy;
}
