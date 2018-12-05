package com.damon.order.domain.trade.command;

import com.damon.oauth.domain.user.aggregate.CartItemId;
import com.damon.oauth.domain.user.aggregate.SkuId;
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
    private final List<CartItemId> cartItemIds;
    private final SkuId skuId;
    private final Integer quantity;
    private final Long promotionId;
    private final Long detailId;
}
