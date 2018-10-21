package com.damon.media.domain.app.command;

import com.damon.media.domain.app.aggregate.AppId;
import com.damon.media.shared.enums.MediaSource;
import com.damon.media.shared.enums.MediaType;
import com.damon.shared.enums.OSCategory;
import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;

/**
 * @author Damon S.
 */
@Getter
@Builder
public class CreateAppCommand {
    private final AppId appId;
    private final String name;
    private final OSCategory os;
    private final MediaType type;
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
    private final Timestamp createdAt;
}
