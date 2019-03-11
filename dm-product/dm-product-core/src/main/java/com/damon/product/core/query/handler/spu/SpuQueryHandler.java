package com.damon.product.core.query.handler.spu;

import com.damon.product.domain.spu.command.FindSpuByIdCommand;
import com.damon.product.domain.spu.command.QuerySpuCommand;
import com.damon.product.domain.spu.entity.QSpuEntry;
import com.damon.product.domain.spu.entity.SpuEntry;
import com.damon.product.domain.spu.entity.SpuRepository;
import com.damon.shared.common.Constants;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
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
        log.trace(Constants.PREFIX_PRODUCT + "=======>handling FindSpuByIdCommand：{}", command);

        return this.jpaQueryFactory.selectFrom(qSpuEntry)
                .where(qSpuEntry.spuId.eq(command.getSpuId()))
                .fetchOne();
    }


    @SuppressWarnings("UnusedDeclaration")
    @QueryHandler
    private QueryResults<SpuEntry> handle(QuerySpuCommand command) {
        log.trace(Constants.PREFIX_PRODUCT + "=======>handling QuerySpuCommand：{}", command);

        final BooleanBuilder whereExpression = new BooleanBuilder();
        // 拼接查询条件
        Optional.ofNullable(command.getName()).ifPresent(
                name -> whereExpression.and(qSpuEntry.name.contains(name))
        );
        Optional.ofNullable(command.getSpuCode()).ifPresent(
                spuCode -> whereExpression.and(qSpuEntry.spuCode.eq(spuCode))
        );
        Optional.ofNullable(command.getType()).ifPresent(
                type -> whereExpression.and(qSpuEntry.type.eq(type.name()))
        );
        Optional.ofNullable(command.getState()).ifPresent(
                spuState -> whereExpression.and(qSpuEntry.state.eq(spuState.name()))
        );
        Optional.ofNullable(command.getVerifyState()).ifPresent(
                verifyState -> whereExpression.and(qSpuEntry.verifyState.eq(verifyState.name()))
        );
        Optional.ofNullable(command.getBrandId()).ifPresent(
                brandId -> whereExpression.and(qSpuEntry.brandId.eq(brandId))
        );
        Optional.ofNullable(command.getCategoryId()).ifPresent(
                categoryId -> whereExpression.and(qSpuEntry.categoryId.eq(categoryId))
        );
        Optional.ofNullable(command.getSupplierId()).ifPresent(
                supplierId -> whereExpression.and(qSpuEntry.supplierId.eq(supplierId))
        );
        Optional.ofNullable(command.getWarehouseId()).ifPresent(
                warehouseId -> whereExpression.and(qSpuEntry.warehouseId.eq(warehouseId))
        );
        Optional.ofNullable(command.getNewProduct()).ifPresent(
                newProduct -> whereExpression.and(qSpuEntry.newProduct.eq(newProduct.name()))
        );
        Optional.ofNullable(command.getRecommended()).ifPresent(
                recommended -> whereExpression.and(qSpuEntry.recommended.eq(recommended.name()))
        );
        Optional.ofNullable(command.getSoldOut()).ifPresent(
                soldOut -> whereExpression.and(qSpuEntry.soldOut.eq(soldOut.name()))
        );
        Optional.ofNullable(command.getSupportReturn()).ifPresent(
                supportReturn -> whereExpression.and(qSpuEntry.supportReturn.eq(supportReturn.name()))
        );
        Optional.ofNullable(command.getDeliveryRegion()).ifPresent(
                deliveryRegion -> whereExpression.and(qSpuEntry.deliveryRegion.contains(deliveryRegion))
        );
        Optional.ofNullable(command.getCreatedBy()).ifPresent(
                createdBy -> whereExpression.and(qSpuEntry.createdBy.eq(createdBy))
        );
        Optional.ofNullable(command.getCreatedTo()).ifPresent(
                createdTo -> whereExpression.and(
                        qSpuEntry.createdAt.before(new Timestamp(createdTo.toEpochMilli()))
                )
        );
        Optional.ofNullable(command.getCreatedFrom()).ifPresent(
                createdFrom -> whereExpression.and(
                        qSpuEntry.createdAt.after(new Timestamp(createdFrom.toEpochMilli()))
                )
        );
        // 获取查询结果
        return this.jpaQueryFactory.selectFrom(qSpuEntry)
                .where(whereExpression)
                .orderBy(qSpuEntry.createdAt.desc(), qSpuEntry.updatedAt.desc())
                .limit(command.getPageSize())
                .offset(command.getPageIndex() - Constants.INT_ONE)
                .fetchResults();
    }
}
