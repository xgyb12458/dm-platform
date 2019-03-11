package com.damon.product.domain.spu.command;

import lombok.RequiredArgsConstructor;
import lombok.Value;

/**
 * 日志查询命令
 * @author Damon S.
 * @version v1.0.1
 * @date 2019年03月10日 21:30
 */
@Value
@RequiredArgsConstructor
public class QueryOperateLogCommand {
    private final String    source;
    private final Long      objectId;
    private final Long      operatedBy;
    private final Long      pageIndex;
    private final Long      pageSize;
}
