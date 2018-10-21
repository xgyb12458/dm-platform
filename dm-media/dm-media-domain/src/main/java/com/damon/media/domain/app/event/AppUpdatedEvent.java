package com.damon.media.domain.app.event;

import com.damon.media.domain.app.aggregate.AppId;
import com.damon.shared.enums.AuditStatus;
import lombok.Builder;
import lombok.Getter;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

import java.time.LocalDateTime;

/**
 * 应用更新事件
 * @author Damon S.
 */
@Getter
@Builder
public class AppUpdatedEvent {
    @TargetAggregateIdentifier
    private final AppId appId;
    private final AuditStatus status;
    private final String industry;
    private final String category;
    private final String keywords;
    private final String snapshot;
    private final String description;
    private final Long updatedBy;
    private final LocalDateTime updatedAt;
}
