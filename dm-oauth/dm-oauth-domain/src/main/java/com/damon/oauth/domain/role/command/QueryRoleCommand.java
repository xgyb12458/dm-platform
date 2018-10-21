package com.damon.oauth.domain.role.command;

import com.damon.oauth.domain.role.aggregate.RoleId;
import com.damon.shared.enums.SwitchState;
import lombok.Builder;
import lombok.Getter;

/**
 * @author Damon S.
 */
@Getter
@Builder
public class QueryRoleCommand {
    private final RoleId roleId;
    private final String code;
    private final String name;
    private final SwitchState state;
    private final Integer pageSize;
    private final Integer pageIndex;
}
