package com.damon.product.core.query.handler.spu;

import com.damon.product.domain.spu.entity.QSpuEntry;
import com.damon.product.domain.spu.entity.SpuEntryRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.axonframework.common.jpa.EntityManagerProvider;
import org.springframework.stereotype.Component;

/**
 * SPU事件处理器
 * @author Damon S.
 */
@Component
public class SpuEventHandler {

    private final JPAQueryFactory jpaQueryFactory;
    private final QSpuEntry qSpuEntry;
    private final SpuEntryRepository spuEntryRepository;

    public SpuEventHandler(EntityManagerProvider managerProvider,
                           SpuEntryRepository spuEntryRepository) {
        this.jpaQueryFactory = new JPAQueryFactory(
                managerProvider.getEntityManager()
        );
        this.qSpuEntry = QSpuEntry.spuEntry;
        this.spuEntryRepository = spuEntryRepository;
    }

}
