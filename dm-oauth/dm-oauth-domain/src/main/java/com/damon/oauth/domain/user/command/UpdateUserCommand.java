package com.damon.oauth.domain.user.command;

import com.damon.oauth.domain.role.aggregate.RoleId;
import lombok.Builder;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

/**
 * 应用更新命令
 * @author Damon S.
 */
@Getter
@Builder
public class UpdateUserCommand {
    @TargetAggregateIdentifier
    private final RoleId roleId;
    private final String name;
    private final String permsJson;
    private final Long updatedBy;
}
