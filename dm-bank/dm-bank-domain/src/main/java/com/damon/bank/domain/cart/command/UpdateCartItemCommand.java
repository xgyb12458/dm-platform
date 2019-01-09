package com.damon.bank.domain.cart.command;

import lombok.Builder;
import lombok.Value;

/**
 * 更改购买商品数量
 * @author Damon S.
 */
@Value
@Builder
public class UpdateCartItemCommand {
    private final Long cartItemId;
    private final Boolean selected;
    private final Integer quantity;
}
