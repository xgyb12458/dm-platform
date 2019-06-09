package com.damon.oauth.domain.resource.aggregate;

import com.damon.oauth.domain.resource.entity.ResourceEntry;
import com.damon.shared.id.impl.SnowflakeIdFactory;
import com.damon.shared.model.AbstractId;


/***
 * 资源ID
 * @author Damon S.
 */
public class ResourceId extends AbstractId<ResourceId, Long> {
    private static final long serialVersionUID = 690159676476L;

    public ResourceId() {
        super(SnowflakeIdFactory.instance().nextId(ResourceEntry.class));
    }

    public ResourceId(Long identifier) {
        super(identifier);
    }
}
