package com.damon.product.domain.sku.aggregate;

import com.damon.shared.model.AbstractId;


/***
 * 地址唯一标识
 * @author Damon S.
 */
public final class AddressId extends AbstractId<AddressId, Long> {
    private static final long serialVersionUID = 1L;

    public AddressId(Long identifier) {
        super(identifier);
    }
}
