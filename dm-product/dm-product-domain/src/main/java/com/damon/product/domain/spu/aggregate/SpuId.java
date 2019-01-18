package com.damon.product.domain.spu.aggregate;

import com.damon.shared.model.AbstractId;


/***
 * 商品单品唯一编号
 * @author Damon S.
 */
public final class SpuId extends AbstractId<SpuId, Long> {
    private static final long serialVersionUID = 1L;

    public SpuId(Long identifier) {
        super(identifier);
    }
}
