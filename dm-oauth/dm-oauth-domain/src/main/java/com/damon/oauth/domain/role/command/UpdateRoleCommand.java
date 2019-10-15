package com.damon.oauth.domain.role.command;

import com.damon.oauth.domain.role.aggregate.RoleId;
import lombok.Builder;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

/**
 * 角色更新命令
 * @author Damon S.
 */
@Getter
@Builder
public class UpdateRoleCommand {
    @TargetAggregateIdentifier
    private final RoleId roleId;
    private final String name;
    private final String permissions;
    private final Long updatedBy;
}
