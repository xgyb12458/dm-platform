package com.damon.oauth.domain.resource.command;

import com.damon.shared.tenant.TenantId;
import lombok.Builder;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

/**
 * 创建资源命令
 * @author Damon S.
 * @version v1.0.1
 * @date 2019年03月26日 09:04
 */
@Getter
@Builder
public class CreateResourceCommand {
    @TargetAggregateIdentifier
    private final Long          resourceId;
    private final String        code;
    private final String        name;
    private final String        platform;
    private final Long          parentId;
    private final TenantId      tenantId;
    private final Long          createdBy;
}
