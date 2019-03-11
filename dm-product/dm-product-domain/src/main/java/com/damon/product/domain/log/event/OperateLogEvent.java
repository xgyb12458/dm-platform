package com.damon.product.domain.log.event;

import lombok.Builder;
import lombok.Value;

/**
 * 日志记录事件
 * @author Damon S.
 * @version v1.0.1
 * @date 2019年03月11日 09:19
 */
@Value
@Builder
public class OperateLogEvent {
    private final Long      objectId;
    private final String    source;
    private final String    type;
    private final Object    content;
    private final Long      operatedBy;
    private final Long      operatedAt;
}
