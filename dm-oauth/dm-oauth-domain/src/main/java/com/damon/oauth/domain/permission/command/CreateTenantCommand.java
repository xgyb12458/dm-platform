package com.damon.oauth.domain.permission.command;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateTenantCommand {
    private final String code;
    private final String name;
    private final Long createdBy;
}
