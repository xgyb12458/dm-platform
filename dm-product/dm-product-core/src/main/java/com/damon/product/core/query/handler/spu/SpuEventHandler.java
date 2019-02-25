package com.damon.product.core.query.handler.spu;

import com.damon.product.domain.spu.entity.QSpuEntry;
import com.damon.product.domain.spu.entity.SpuRepository;
import com.damon.product.domain.spu.event.SpuCreatedEvent;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.axonframework.common.jpa.EntityManagerProvider;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

/**
 * SPU事件处理器
 * @author Damon S.
 */
@Component
public class SpuEventHandler {

    private final JPAQueryFactory jpaQueryFactory;
    private final QSpuEntry qSpuEntry;
    private final SpuRepository spuRepository;

    public SpuEventHandler(EntityManagerProvider managerProvider,
                           SpuRepository spuRepository) {
        this.jpaQueryFactory = new JPAQueryFactory(
                managerProvider.getEntityManager()
        );
        this.qSpuEntry = QSpuEntry.spuEntry;
        this.spuRepository = spuRepository;
    }


    @SuppressWarnings("UnusedDeclaration")
    @EventHandler
    private void on(SpuCreatedEvent event) {

    }
}
