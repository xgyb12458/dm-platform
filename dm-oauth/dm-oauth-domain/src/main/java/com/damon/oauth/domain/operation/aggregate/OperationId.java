package com.damon.oauth.domain.operation.aggregate;

import com.damon.oauth.domain.operation.entity.OperationEntry;
import com.damon.shared.common.IdFactory;
import com.damon.shared.model.AbstractId;


/***
 * 操作ID
 * @author Damon S.
 */
public class OperationId extends AbstractId<OperationId, Long> {
    private static final long serialVersionUID = 690159676476L;

    public OperationId() {
        super(IdFactory.instance().nextId(OperationEntry.class));
    }

    public OperationId(Long identifier) {
        super(identifier);
    }
}
