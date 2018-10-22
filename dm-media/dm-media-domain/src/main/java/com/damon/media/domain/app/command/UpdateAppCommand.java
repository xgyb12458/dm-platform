package com.damon.media.domain.app.command;

import com.damon.media.domain.app.aggregate.AppId;
import lombok.Builder;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

/**
 * 应用更新命令
 * @author Damon S.
 */
@Getter
@Builder
public class UpdateAppCommand {
    @TargetAggregateIdentifier
    private final AppId appId;
    private final String industry;
    private final String category;
    private final String keywords;
    private final String snapshot;
    private final String description;
    private final Long updatedBy;
}
