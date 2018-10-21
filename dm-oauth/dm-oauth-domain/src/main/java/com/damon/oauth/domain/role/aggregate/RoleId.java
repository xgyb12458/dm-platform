package com.damon.oauth.domain.role.aggregate;

import com.damon.oauth.domain.role.entity.RoleEntry;
import com.damon.shared.common.IdFactory;
import com.damon.shared.model.AbstractIdentifier;


/***
 * 资源位规格ID
 * @author Damon S.
 */
public class RoleId extends AbstractIdentifier<RoleId, Long> {
    private static final long serialVersionUID = 690159676476L;

    public RoleId() {
        super(IdFactory.instance().nextId(RoleEntry.class));
    }

    public RoleId(Long identifier) {
        super(identifier);
    }
}
