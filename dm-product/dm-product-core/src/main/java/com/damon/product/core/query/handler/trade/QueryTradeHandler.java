package com.damon.product.core.query.handler.trade;

import com.damon.product.domain.trade.entity.QTradeEntry;
import com.damon.product.domain.trade.entity.TradeEntryRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.axonframework.common.jpa.EntityManagerProvider;
import org.springframework.stereotype.Component;

/**
 * @author Damon S.
 */
@Component
public class QueryTradeHandler {

    private final JPAQueryFactory jpaQueryFactory;
    private final QTradeEntry qTradeEntry;
    private final TradeEntryRepository tradeEntryRepository;

    public QueryTradeHandler(EntityManagerProvider managerProvider,
                             TradeEntryRepository tradeEntryRepository) {
        this.jpaQueryFactory = new JPAQueryFactory(
                managerProvider.getEntityManager()
        );
        this.qTradeEntry = QTradeEntry.tradeEntry;
        this.tradeEntryRepository = tradeEntryRepository;
    }

}
