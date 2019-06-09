package com.damon.oauth.domain.role.aggregate;

import com.damon.oauth.domain.role.entity.RoleEntry;
import com.damon.shared.id.impl.SnowflakeIdFactory;
import com.damon.shared.model.AbstractId;


/***
 * 资源位规格ID
 * @author Damon S.
 */
public class RoleId extends AbstractId<RoleId, Long> {
    private static final long serialVersionUID = 690159676476L;

    public RoleId() {
        super(SnowflakeIdFactory.instance().nextId(RoleEntry.class));
    }

    public RoleId(Long identifier) {
        super(identifier);
    }
}
