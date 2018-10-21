package com.damon.media.core.query.handler.layout;

import com.damon.media.domain.slot.layout.command.QueryLayoutCommand;
import com.damon.media.domain.slot.layout.entity.FeedLayoutEntry;
import com.damon.media.domain.slot.layout.entity.QFeedLayoutEntry;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.axonframework.common.jpa.EntityManagerProvider;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * 与查询信息流布局样式相关的功能
 * @author Damon S.
 */
@Component
public class QueryLayoutHandler {

    private final JPAQueryFactory jpaQueryFactory;
    private final QFeedLayoutEntry qLayoutEntry;

    public QueryLayoutHandler(EntityManagerProvider managerProvider) {
        this.jpaQueryFactory = new JPAQueryFactory(
                managerProvider.getEntityManager()
        );
        this.qLayoutEntry = QFeedLayoutEntry.feedLayoutEntry;
    }


    @SuppressWarnings("unused")
    @QueryHandler
    private QueryResults<FeedLayoutEntry> handle(QueryLayoutCommand query) {
        final BooleanBuilder whereExpression = new BooleanBuilder();
        Optional.ofNullable(query.getWidth()).ifPresent(
                w -> whereExpression.and(qLayoutEntry.width.eq(w))
        );
        Optional.ofNullable(query.getHeight()).ifPresent(
                h -> whereExpression.and(qLayoutEntry.height.eq(h))
        );
        Optional.ofNullable(query.getState()).ifPresent(
                s -> whereExpression.and(qLayoutEntry.state.eq(s.name()))
        );
        Optional.ofNullable(query.getType()).ifPresent(
                t -> whereExpression.and(qLayoutEntry.layoutType.eq(t.name()))
        );
        return jpaQueryFactory.selectFrom(qLayoutEntry)
                .where(whereExpression)
                .orderBy(qLayoutEntry.state.desc(), qLayoutEntry.updatedAt.desc())
                .limit(query.getPageSize())
                .offset(query.getPageIndex() - 1)
                .fetchResults();
    }


    @SuppressWarnings("unused")
    @QueryHandler
    private FeedLayoutEntry handle(Long layoutId) {
        return jpaQueryFactory.selectFrom(qLayoutEntry)
                .where(qLayoutEntry.layoutId.eq(layoutId))
                .fetchOne();
    }
}
