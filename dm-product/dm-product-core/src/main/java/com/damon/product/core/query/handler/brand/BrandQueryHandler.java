package com.damon.product.core.query.handler.brand;

import com.damon.product.domain.brand.command.FindBrandByIdCommand;
import com.damon.product.domain.brand.command.QueryBrandCommand;
import com.damon.product.domain.brand.entity.BrandEntry;
import com.damon.product.domain.brand.entity.BrandRepository;
import com.damon.product.domain.brand.entity.QBrandEntry;
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
 * 类别查询处理器
 * @author Damon S.
 * @version v1.0.1
 * @date 2019年02月28日 22:06
 */
@Slf4j
@Component
public class BrandQueryHandler {
    
    private final JPAQueryFactory jpaQueryFactory;
    private final QBrandEntry qBrandEntry;
    private final BrandRepository brandRepository;

    public BrandQueryHandler(EntityManagerProvider managerProvider,
                             BrandRepository brandRepository) {
        this.jpaQueryFactory = new JPAQueryFactory(
                managerProvider.getEntityManager()
        );
        this.brandRepository = brandRepository;
        this.qBrandEntry = QBrandEntry.brandEntry;
    }


    @SuppressWarnings("UnusedDeclaration")
    @QueryHandler
    private BrandEntry handle(FindBrandByIdCommand command) {
        log.trace(Constants.PREFIX_PRODUCT + "=======>handling FindBrandByIdCommand：{}", command);

        return this.jpaQueryFactory.selectFrom(qBrandEntry)
                .where(qBrandEntry.brandId.eq(command.getBrandId()))
                .fetchOne();
    }


    @SuppressWarnings("UnusedDeclaration")
    @QueryHandler
    private QueryResults handle(QueryBrandCommand command) {
        log.trace(Constants.PREFIX_PRODUCT + "=======>handling QueryBrandCommand：{}", command);

        final BooleanBuilder whereExpression = new BooleanBuilder();
        // 拼接查询条件
        Optional.ofNullable(command.getName()).ifPresent(
                name -> whereExpression.and(qBrandEntry.name.contains(name))
        );
        Optional.ofNullable(command.getCode()).ifPresent(
                code -> whereExpression.and(qBrandEntry.code.eq(code))
        );
        Optional.ofNullable(command.getCreatedBy()).ifPresent(
                createdBy -> whereExpression.and(qBrandEntry.createdBy.eq(createdBy))
        );
        Optional.ofNullable(command.getFactoryState()).ifPresent(
                state -> whereExpression.and(qBrandEntry.factoryState.eq(state.name()))
        );
        Optional.ofNullable(command.getFirstLetter()).ifPresent(
                letter -> whereExpression.and(qBrandEntry.firstLetter.eq(letter))
        );
        Optional.ofNullable(command.getRemoved()).ifPresent(
                removed -> whereExpression.and(qBrandEntry.removed.eq(removed.name()))
        );
        Optional.ofNullable(command.getCreatedFrom()).ifPresent(
                from -> whereExpression.and(
                        qBrandEntry.createdAt.after(new Timestamp(from.toEpochMilli()))
                )
        );
        Optional.ofNullable(command.getCreatedTo()).ifPresent(
                to -> whereExpression.and(
                        qBrandEntry.createdAt.before(new Timestamp(to.toEpochMilli()))
                )
        );
        // 获取查询结果
        return this.jpaQueryFactory.selectFrom(qBrandEntry)
                .where(whereExpression)
                .orderBy(qBrandEntry.createdAt.desc(), qBrandEntry.updatedAt.desc())
                .limit(command.getPageSize())
                .offset(command.getPageIndex() - Constants.INT_ONE)
                .fetchResults();
    }
}
