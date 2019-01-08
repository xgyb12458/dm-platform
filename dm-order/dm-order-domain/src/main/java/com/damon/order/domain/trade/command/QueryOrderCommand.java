package com.damon.order.domain.trade.command;

import com.damon.order.shared.enums.OrderState;
import com.damon.order.shared.enums.PaidState;
import lombok.Builder;
import lombok.Value;

/**
 * @author Damon S.
 */
@Value
@Builder
public class QueryOrderCommand {
    private final Long userId;
    private final PaidState paidState;
    private final OrderState orderState;
    private final Long requestedBy;
    private final Integer pageSize;
    private final Integer pageIndex;
}
