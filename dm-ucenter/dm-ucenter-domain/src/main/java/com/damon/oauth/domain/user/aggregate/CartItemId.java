package com.damon.oauth.domain.user.aggregate;

import com.damon.shared.model.AbstractId;


/***
 * 购物车项唯一标识
 * @author Damon S.
 */
public final class CartItemId extends AbstractId<CartItemId, Long> {
    private static final long serialVersionUID = 1L;

    public CartItemId(Long identifier) {
        super(identifier);
    }
}
