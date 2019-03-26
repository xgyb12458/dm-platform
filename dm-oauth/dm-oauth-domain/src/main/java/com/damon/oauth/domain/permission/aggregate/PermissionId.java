package com.damon.oauth.domain.permission.aggregate;

import com.damon.oauth.domain.permission.entity.PermissionEntry;
import com.damon.shared.common.IdFactory;
import com.damon.shared.model.AbstractId;


/***
 * 资源位规格ID
 * @author Damon S.
 */
public class PermissionId extends AbstractId<PermissionId, Long> {
    private static final long serialVersionUID = 690159676476L;

    public PermissionId() {
        super(IdFactory.instance().nextId(PermissionEntry.class));
    }

    public PermissionId(Long identifier) {
        super(identifier);
    }
}
