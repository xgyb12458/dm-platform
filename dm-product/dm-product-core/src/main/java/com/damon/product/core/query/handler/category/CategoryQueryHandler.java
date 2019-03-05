package com.damon.product.core.query.handler.category;

import com.damon.product.domain.category.command.FindCategoryByIdCommand;
import com.damon.product.domain.category.command.QueryCategoryCommand;
import com.damon.product.domain.category.entity.CategoryEntry;
import com.damon.product.domain.category.entity.CategoryRepository;
import com.damon.product.domain.category.entity.QCategoryEntry;
import com.damon.shared.common.Constants;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.common.jpa.EntityManagerProvider;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * 类别查询处理器
 * @author Damon S.
 * @version v1.0.1
 * @date 2019年02月28日 11:27
 */
@Slf4j
@Component
public class CategoryQueryHandler {

    private final JPAQueryFactory jpaQueryFactory;
    private final QCategoryEntry qCategoryEntry;
    private final CategoryRepository categoryRepository;

    public CategoryQueryHandler(EntityManagerProvider managerProvider,
                                CategoryRepository categoryRepository) {
        this.jpaQueryFactory = new JPAQueryFactory(
                managerProvider.getEntityManager()
        );
        this.categoryRepository = categoryRepository;
        this.qCategoryEntry = QCategoryEntry.categoryEntry;
    }


    @SuppressWarnings("UnusedDeclaration")
    @QueryHandler
    private CategoryEntry handle(FindCategoryByIdCommand command) {
        log.trace(Constants.PREFIX_PRODUCT + "=======>handling FindCategoryByIdCommand：{}", command);

        return this.jpaQueryFactory.selectFrom(qCategoryEntry)
                .where(qCategoryEntry.categoryId.eq(command.getCategoryId()))
                .fetchOne();
    }


    @SuppressWarnings("UnusedDeclaration")
    @QueryHandler
    private QueryResults<CategoryEntry> handle(QueryCategoryCommand command) {
        log.trace(Constants.PREFIX_PRODUCT + "=======>handling QueryCategoryCommand：{}", command);

        final BooleanBuilder whereExpression = new BooleanBuilder();
        // 拼接查询条件
        Optional.ofNullable(command.getName()).ifPresent(
                name -> whereExpression.and(qCategoryEntry.name.like(name))
        );
        Optional.ofNullable(command.getKeywords()).ifPresent(
                keywords -> whereExpression.and(qCategoryEntry.keywords.like(keywords))
        );
        Optional.ofNullable(command.getNavState()).ifPresent(
                navState -> whereExpression.and(qCategoryEntry.navState.eq(navState.name()))
        );
        Optional.ofNullable(command.getShowState()).ifPresent(
                showState -> whereExpression.and(qCategoryEntry.showState.eq(showState.name()))
        );
        Optional.ofNullable(command.getParentId()).ifPresent(
                parentId -> whereExpression.and(qCategoryEntry.parentId.eq(parentId))
        );
        // 获取查询结果
        return this.jpaQueryFactory.selectFrom(qCategoryEntry)
                .where(whereExpression)
                .orderBy(qCategoryEntry.createdAt.desc(), qCategoryEntry.updatedAt.desc())
                .limit(command.getPageSize())
                .offset(command.getPageIndex() - Constants.INT_ONE)
                .fetchResults();
    }
}
