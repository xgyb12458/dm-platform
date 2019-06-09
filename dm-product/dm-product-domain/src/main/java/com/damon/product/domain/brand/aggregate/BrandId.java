package com.damon.product.domain.brand.aggregate;

import com.damon.product.domain.brand.entity.BrandEntry;
import com.damon.shared.id.impl.SnowflakeIdFactory;
import com.damon.shared.model.AbstractId;


/***
 * 商品品牌唯一编号
 * @author Damon S.
 */
public final class BrandId extends AbstractId<BrandId, Long> {
    private static final long serialVersionUID = 1L;

    public BrandId() {super(SnowflakeIdFactory.instance().nextId(BrandEntry.class));}

    public BrandId(Long identifier) {
        super(identifier);
    }
}
