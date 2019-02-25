package com.damon.product.core.query.handler.spu;

import com.damon.product.domain.spu.command.FindSpuByIdCommand;
import com.damon.product.domain.spu.command.QuerySpuCommand;
import com.damon.product.domain.spu.entity.QSpuEntry;
import com.damon.product.domain.spu.entity.SpuEntry;
import com.damon.product.domain.spu.entity.SpuRepository;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.common.jpa.EntityManagerProvider;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Optional;

/**
 * SPU查询处理器
 * @author Damon S.
 */
@Slf4j
@Component
public class SpuQueryHandler {

    private final JPAQueryFactory jpaQueryFactory;
    private final QSpuEntry qSpuEntry;
    private final SpuRepository spuRepository;

    public SpuQueryHandler(EntityManagerProvider managerProvider,
                           SpuRepository spuRepository) {
        this.jpaQueryFactory = new JPAQueryFactory(
                managerProvider.getEntityManager()
        );
        this.spuRepository = spuRepository;
        this.qSpuEntry = QSpuEntry.spuEntry;
    }


    @SuppressWarnings("UnusedDeclaration")
    @QueryHandler
    private SpuEntry handle(FindSpuByIdCommand command) {
        log.trace("=======>handling FindSpuByIdCommand：{}", command);

        return this.jpaQueryFactory.selectFrom(qSpuEntry)
                .where(qSpuEntry.spuId.eq(command.getSpuId()))
                .fetchOne();
    }


    @SuppressWarnings("UnusedDeclaration")
    @QueryHandler
    private QueryResults<SpuEntry> handle(QuerySpuCommand command) {
        log.trace("=======>handling QuerySpuCommand：{}", command);

        JPAQuery<SpuEntry> spuEntryQuery = jpaQueryFactory.selectFrom(qSpuEntry);

        // 拼接查询条件
        Optional.ofNullable(command.getName()).ifPresent(
                name -> spuEntryQuery.where(qSpuEntry.name.like(name))
        );
        Optional.ofNullable(command.getSpuCode()).ifPresent(
                spuCode -> spuEntryQuery.where(qSpuEntry.spuCode.eq(spuCode))
        );
        Optional.ofNullable(command.getBrandId()).ifPresent(
                brandId -> spuEntryQuery.where(qSpuEntry.brandId.eq(brandId))
        );
        Optional.ofNullable(command.getCategoryId()).ifPresent(
                categoryId -> spuEntryQuery.where(qSpuEntry.categoryId.eq(categoryId))
        );
        Optional.ofNullable(command.getSupplierId()).ifPresent(
                supplierId -> spuEntryQuery.where(qSpuEntry.supplierId.eq(supplierId))
        );
        Optional.ofNullable(command.getWarehouseId()).ifPresent(
                warehouseId -> spuEntryQuery.where(qSpuEntry.warehouseId.eq(warehouseId))
        );
        Optional.ofNullable(command.getDeliveryRegion()).ifPresent(
                deliveryRegion -> spuEntryQuery.where(qSpuEntry.deliveryRegion.like(deliveryRegion))
        );
        Optional.ofNullable(command.getModel()).ifPresent(
                model -> spuEntryQuery.where(qSpuEntry.model.eq(model))
        );
        Optional.ofNullable(command.getNewProduct()).ifPresent(
                newProduct -> spuEntryQuery.where(qSpuEntry.newProduct.eq(newProduct.name()))
        );
        Optional.ofNullable(command.getRecommended()).ifPresent(
                recommended -> spuEntryQuery.where(qSpuEntry.recommended.eq(recommended.name()))
        );
        Optional.ofNullable(command.getSoldOut()).ifPresent(
                soldOut -> spuEntryQuery.where(qSpuEntry.soldOut.eq(soldOut.name()))
        );
        Optional.ofNullable(command.getState()).ifPresent(
                spuState -> spuEntryQuery.where(qSpuEntry.state.eq(spuState.name()))
        );
        Optional.ofNullable(command.getSupportReturn()).ifPresent(
                supportReturn -> spuEntryQuery.where(qSpuEntry.supportReturn.eq(supportReturn.name()))
        );
        Optional.ofNullable(command.getVerifyState()).ifPresent(
                verifyState -> spuEntryQuery.where(qSpuEntry.verifyState.eq(verifyState.name()))
        );
        Optional.ofNullable(command.getType()).ifPresent(
                type -> spuEntryQuery.where(qSpuEntry.type.eq(type.name()))
        );
        Optional.ofNullable(command.getCreatedBy()).ifPresent(
                createdBy -> spuEntryQuery.where(qSpuEntry.createdBy.eq(createdBy))
        );
        Optional.ofNullable(command.getCreatedFrom()).ifPresent(
                createdFrom -> spuEntryQuery.where(
                        qSpuEntry.createdAt.after(new Timestamp(createdFrom.toEpochMilli()))
                )
        );
        Optional.ofNullable(command.getCreatedTo()).ifPresent(
                createdTo -> spuEntryQuery.where(
                        qSpuEntry.createdAt.before(new Timestamp(createdTo.toEpochMilli()))
                )
        );
        // 获取查询结果
        return spuEntryQuery.fetchResults();
    }
}
