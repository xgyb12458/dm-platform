package com.damon.oauth.domain.resource.command;

import lombok.Builder;
import lombok.Getter;

/**
 * 查询资源信息
 * @author Damon S.
 * @version v1.0.1
 * @date 2019年04月19日 13:55
 */
@Getter
@Builder
public class QueryResourceCommand {
    private final String        code;
    private final String        name;
    private final String        platform;
    private final Long          parentId;
    private final Long          createdBy;
    private final Long          updatedBy;
    private final Long          createdAt;
    private final Long          updatedAt;
}
