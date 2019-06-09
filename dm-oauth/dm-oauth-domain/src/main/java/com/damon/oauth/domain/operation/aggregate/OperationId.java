package com.damon.oauth.domain.operation.aggregate;

import com.damon.oauth.domain.operation.entity.OperationEntry;
import com.damon.shared.id.impl.SnowflakeIdFactory;
import com.damon.shared.model.AbstractId;


/***
 * 操作ID
 * @author Damon S.
 */
public class OperationId extends AbstractId<OperationId, Long> {
    private static final long serialVersionUID = 690159676476L;

    public OperationId() {
        super(SnowflakeIdFactory.instance().nextId(OperationEntry.class));
    }

    public OperationId(Long identifier) {
        super(identifier);
    }
}
