package com.damon.order.domain.trade.command;

import lombok.Builder;
import lombok.Getter;

/**
 * 确认订单查询命令
 * @author Damon S.
 */
@Getter
@Builder
public class ConfirmOrderCommand {
    private final String command;
}
