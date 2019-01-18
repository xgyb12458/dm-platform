package com.damon.product.domain.sku.aggregate;

import com.damon.shared.model.AbstractId;


/***
 * 发票唯一标识
 * @author Damon S.
 */
public final class InvoiceId extends AbstractId<InvoiceId, Long> {
    private static final long serialVersionUID = 1L;

    public InvoiceId(Long identifier) {
        super(identifier);
    }
}
