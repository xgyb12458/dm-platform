package com.damon.order.domain.trade.command;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

/**
 * 确认订单查询命令
 * @author Damon S.
 */
@Getter
@Builder
public class ConfirmOrderCommand {
    private final List<String> cartItems;
    private final String sku;
    private final Integer qty;
    private final String pid;
    private final String did;
}
