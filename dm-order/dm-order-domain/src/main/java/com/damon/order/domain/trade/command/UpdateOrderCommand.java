package com.damon.order.domain.trade.command;

import lombok.Builder;
import lombok.Value;

/**
 * 修改订单信息命令
 * @author Damon S.
 */
@Value
@Builder
public class UpdateOrderCommand {
    private final Long requestedBy;
}
