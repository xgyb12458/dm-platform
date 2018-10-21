package com.damon.order.domain.trade.aggregate;

import com.damon.order.domain.trade.entity.TradeEntry;
import com.damon.shared.common.IdFactory;
import com.damon.shared.model.AbstractIdentifier;


/***
 * 资源位规格ID
 * @author Damon S.
 */
public class TradeId extends AbstractIdentifier<TradeId, Long> {
    private static final long serialVersionUID = 1L;

    public TradeId() {
        super(IdFactory.instance().nextId(TradeEntry.class));
    }

    public TradeId(Long identifier) {
        super(identifier);
    }
}
