package com.damon.media.domain.app.event;

import com.damon.media.domain.app.aggregate.AppAggregate;
import com.damon.media.domain.app.aggregate.AppId;
import com.damon.media.shared.enums.MediaSource;
import com.damon.media.shared.enums.MediaType;
import com.damon.shared.enums.AuditStatus;
import com.damon.shared.enums.OSCategory;
import com.damon.shared.enums.SwitchState;
import lombok.Builder;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.time.LocalDateTime;


/***
 * 应用创建完成事件
 * @author Damon S.
 */
@Getter
@Builder
public class AppCreatedEvent {
    @TargetAggregateIdentifier
    private final AppId appId;
    private final String name;
    private final OSCategory os;
    private final AppAggregate.AccessEncrypt accessEncrypt;
    private final MediaType type;
    private final AuditStatus status;
    private final SwitchState state;
    private final MediaSource source;
    private final String downloadUrl;
    private final String packageName;
    private final String industry;
    private final String category;
    private final String keywords;
    private final String snapshot;
    private final String description;
    private final Long userId;
    private final Long createdBy;
    private final LocalDateTime createdAt;
}
