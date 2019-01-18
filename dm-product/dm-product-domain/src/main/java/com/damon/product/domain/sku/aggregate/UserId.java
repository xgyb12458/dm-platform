package com.damon.product.domain.sku.aggregate;

import com.damon.shared.model.AbstractId;


/***
 * 资源位规格ID
 * @author Damon S.
 */
public final class UserId extends AbstractId<UserId, Long> {
    private static final long serialVersionUID = 1L;

    public UserId(Long identifier) {
        super(identifier);
    }
}
