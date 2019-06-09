package com.damon.oauth.domain.permission.aggregate;

import com.damon.oauth.domain.permission.entity.PermissionEntry;
import com.damon.shared.id.impl.SnowflakeIdFactory;
import com.damon.shared.model.AbstractId;


/***
 * 权限ID
 * @author Damon S.
 */
public class PermissionId extends AbstractId<PermissionId, Long> {
    private static final long serialVersionUID = 690159676476L;

    public PermissionId() {
        super(SnowflakeIdFactory.instance().nextId(PermissionEntry.class));
    }

    public PermissionId(Long identifier) {
        super(identifier);
    }
}
