package com.damon.product.core.query.handler.sku;


import com.damon.product.domain.sku.entity.QSkuEntry;
import com.damon.product.domain.sku.entity.SkuRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.common.jpa.EntityManagerProvider;
import org.springframework.stereotype.Component;

/**
 * SKU事件处理器
 * @author Damon S.
 */
@Slf4j
@Component
public class SkuQueryHandler {

    private final JPAQueryFactory jpaQueryFactory;
    private final QSkuEntry qSkuEntry;
    private final SkuRepository skuRepository;

    public SkuQueryHandler(EntityManagerProvider managerProvider,
                           SkuRepository skuRepository) {
        this.jpaQueryFactory = new JPAQueryFactory(
                managerProvider.getEntityManager()
        );
        this.skuRepository = skuRepository;
        this.qSkuEntry = QSkuEntry.skuEntry;
    }
}
