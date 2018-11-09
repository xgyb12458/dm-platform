package com.damon.oauth.domain.user.aggregate;

import com.damon.shared.model.AbstractIdentifier;


/***
 * 资源位规格ID
 * @author Damon S.
 */
public class InvoiceId extends AbstractIdentifier<InvoiceId, Long> {
    private static final long serialVersionUID = 1L;

    public InvoiceId(Long identifier) {
        super(identifier);
    }
}
