package com.damon.product.domain.spu.aggregate;

import com.damon.product.domain.spu.entity.SpuEntry;
import com.damon.shared.id.impl.SnowflakeIdFactory;
import com.damon.shared.model.AbstractId;


/***
 * 商品唯一编号
 * @author Damon S.
 */
public final class SpuId extends AbstractId<SpuId, Long> {
    private static final long serialVersionUID = 1L;

    public SpuId() {super(SnowflakeIdFactory.instance().nextId(SpuEntry.class));}

    public SpuId(Long identifier) {
        super(identifier);
    }
}
