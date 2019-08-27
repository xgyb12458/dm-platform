package com.damon.oauth.domain.resource.command;

import lombok.Builder;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

/**
 * 更新资源信息
 * @author Damon S.
 * @version v1.0.1
 * @date 2019年04月19日 13:55
 */
@Getter
@Builder
public class UpdateResourceCommand {
    @TargetAggregateIdentifier
    private final Long          resourceId;
//    private final String        code;
    private final String        name;
    private final String        platform;
//    private final Long          parentId;
    private final Long          updatedBy;
}
