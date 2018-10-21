package com.damon.order.core.query.handler.user;

import com.damon.oauth.domain.user.entity.QUserEntry;
import com.damon.order.domain.trade.entity.TradeEntryRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.axonframework.common.jpa.EntityManagerProvider;
import org.springframework.stereotype.Component;

/**
 * @author Damon S.
 */
@Component
public class QueryUserHandler {

    private final JPAQueryFactory jpaQueryFactory;
    private final QUserEntry qUserEntry;
    private final TradeEntryRepository userEntryRepository;

    public QueryUserHandler(EntityManagerProvider managerProvider,
                            TradeEntryRepository userEntryRepository) {
        this.jpaQueryFactory = new JPAQueryFactory(
                managerProvider.getEntityManager()
        );
        this.qUserEntry = QUserEntry.userEntry;
        this.userEntryRepository = userEntryRepository;
    }
}
