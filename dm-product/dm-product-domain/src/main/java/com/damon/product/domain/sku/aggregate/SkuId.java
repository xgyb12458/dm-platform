package com.damon.product.domain.sku.aggregate;

import com.damon.product.domain.sku.entity.SkuEntry;
import com.damon.shared.id.impl.SnowflakeIdFactory;
import com.damon.shared.model.AbstractId;


/***
 * 商品单品唯一编号
 * @author Damon S.
 */
public final class SkuId extends AbstractId<SkuId, Long> {
    private static final long serialVersionUID = 1L;

    public SkuId() {super(SnowflakeIdFactory.instance().nextId(SkuEntry.class));}

    public SkuId(Long identifier) {
        super(identifier);
    }
}
