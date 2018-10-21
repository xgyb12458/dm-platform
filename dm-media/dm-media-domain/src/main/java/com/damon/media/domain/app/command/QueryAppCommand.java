package com.damon.media.domain.app.command;

import com.damon.media.shared.enums.MediaSource;
import com.damon.media.shared.enums.MediaType;
import com.damon.shared.enums.*;
import lombok.Builder;
import lombok.Getter;

/**
 * @author Damon S.
 */
@Getter
@Builder
public class QueryAppCommand {
    private final String name;
    private final OSCategory os;
    private final MediaType type;
    private final MediaSource source;
    private final AuditStatus status;
    private final SwitchState state;
    private final String industry;
    private final Long userId;
    private final Integer pageSize;
    private final Integer pageIndex;
}
