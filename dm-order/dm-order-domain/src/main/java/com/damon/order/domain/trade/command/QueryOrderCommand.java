package com.damon.order.domain.trade.command;

import com.damon.order.shared.enums.OrderState;
import com.damon.order.shared.enums.PayState;
import lombok.Builder;
import lombok.Value;

/**
 * @author Damon S.
 */
@Value
@Builder
public class QueryOrderCommand {
    private final Long userId;
    private final PayState payState;
    private final OrderState orderState;
    private final Integer pageSize;
    private final Integer pageIndex;
}
