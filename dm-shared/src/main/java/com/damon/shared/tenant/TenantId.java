package com.damon.shared.tenant;

import com.damon.shared.common.IdFactory;
import com.damon.shared.model.AbstractId;


/***
 * 资源位规格ID
 * @author Damon S.
 */
public class TenantId extends AbstractId<TenantId, Long> {
    private static final long serialVersionUID = 690159676476L;

    public TenantId() {
        super(IdFactory.instance().nextId());
    }

    public TenantId(Long identifier) {
        super(identifier);
    }
}
