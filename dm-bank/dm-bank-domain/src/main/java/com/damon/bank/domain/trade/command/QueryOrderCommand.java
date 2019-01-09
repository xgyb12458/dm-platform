package com.damon.bank.domain.trade.command;

import com.damon.bank.shared.enums.OrderState;
import com.damon.bank.shared.enums.PaidState;
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
