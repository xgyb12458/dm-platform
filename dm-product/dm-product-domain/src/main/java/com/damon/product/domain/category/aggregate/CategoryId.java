package com.damon.product.domain.category.aggregate;

import com.damon.product.domain.category.entity.CategoryEntry;
import com.damon.shared.common.IdFactory;
import com.damon.shared.model.AbstractId;


/***
 * 商品品类唯一编号
 * @author Damon S.
 */
public final class CategoryId extends AbstractId<CategoryId, Long> {
    private static final long serialVersionUID = 1L;

    public CategoryId() {super(IdFactory.instance().nextId(CategoryEntry.class));}

    public CategoryId(Long identifier) {
        super(identifier);
    }
}
