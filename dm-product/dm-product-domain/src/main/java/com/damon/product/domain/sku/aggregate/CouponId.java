package com.damon.product.domain.sku.aggregate;

import com.damon.shared.model.AbstractId;


/***
 * 资源位规格ID
 * @author Damon S.
 */
public final class CouponId extends AbstractId<CouponId, Long> {
    private static final long serialVersionUID = 1L;

    public CouponId(Long identifier) {
        super(identifier);
    }
}
