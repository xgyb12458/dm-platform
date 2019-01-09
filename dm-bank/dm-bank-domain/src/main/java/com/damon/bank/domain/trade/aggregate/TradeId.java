package com.damon.bank.domain.trade.aggregate;

import com.damon.bank.domain.trade.entity.TradeEntry;
import com.damon.shared.common.IdFactory;
import com.damon.shared.model.AbstractId;


/***
 * 交易唯一标识
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
