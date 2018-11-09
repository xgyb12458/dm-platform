package com.damon.oauth.domain.user.aggregate;

import com.damon.shared.model.AbstractId;


/***
 * 资源位规格ID
 * @author Damon S.
 */
public final class SkuId extends AbstractId<SkuId, Long> {
    private static final long serialVersionUID = 1L;

    public SkuId(Long identifier) {
        super(identifier);
    }
}
