package com.damon.oauth.domain.resource.command;

import com.damon.shared.tenant.TenantId;
import lombok.Builder;
import lombok.Value;

/**
 * 创建资源命令
 * @author Damon S.
 * @version v1.0.1
 * @date 2019年03月26日 09:04
 */
@Value
@Builder
public class CreateResourceCommand {
    private final Long          resourceId;
    private final String        code;
    private final String        name;
    private final String        path;
    private final Integer       sort;
    private final String        platform;
    private final Long          parentId;
    private final TenantId      tenantId;
    private final Long          createdBy;
}
