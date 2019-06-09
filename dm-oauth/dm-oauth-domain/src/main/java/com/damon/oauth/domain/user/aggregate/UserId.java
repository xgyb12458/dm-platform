package com.damon.oauth.domain.user.aggregate;

import com.damon.oauth.domain.user.entity.UserEntry;
import com.damon.shared.id.impl.SnowflakeIdFactory;
import com.damon.shared.model.AbstractId;


/***
 * 资源位规格ID
 * @author Damon S.
 */
public class UserId extends AbstractId<UserId, Long> {
    private static final long serialVersionUID = 1L;

    public UserId() {
        super(SnowflakeIdFactory.instance().nextId(UserEntry.class));
    }

    public UserId(Long identifier) {
        super(identifier);
    }
}
