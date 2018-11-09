package com.damon.order.domain.trade.aggregate;

import com.damon.order.domain.trade.entity.TradeEntry;
import com.damon.shared.common.IdFactory;
import com.damon.shared.model.AbstractId;


/***
 * 资源位规格ID
 * @author Damon S.
 */
public final class TradeId extends AbstractId<TradeId, Long> {
    private static final long serialVersionUID = 1L;

    public TradeId() {
        super(IdFactory.instance().nextId(TradeEntry.class));
    }

    public TradeId(Long identifier) {
        super(identifier);
    }
}
