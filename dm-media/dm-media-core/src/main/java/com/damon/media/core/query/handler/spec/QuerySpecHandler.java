package com.damon.media.core.query.handler.spec;

import com.damon.media.domain.slot.spec.command.QuerySpecCommand;
import com.damon.media.domain.slot.spec.entity.QSlotSpecEntry;
import com.damon.media.domain.slot.spec.entity.SlotSpecEntry;
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
public class QuerySpecHandler {

    private final JPAQueryFactory jpaQueryFactory;
    private final QSlotSpecEntry qSlotSpecEntry;

    public QuerySpecHandler(EntityManagerProvider managerProvider) {
        this.jpaQueryFactory = new JPAQueryFactory(
                managerProvider.getEntityManager()
        );
        this.qSlotSpecEntry = QSlotSpecEntry.slotSpecEntry;
    }


    @QueryHandler
    private QueryResults<SlotSpecEntry> handle(QuerySpecCommand query) {
        final BooleanBuilder whereExpression = new BooleanBuilder();
        Optional.ofNullable(query.getWidth()).ifPresent(
                w -> whereExpression.and(qSlotSpecEntry.width.eq(w))
        );
        Optional.ofNullable(query.getHeight()).ifPresent(
                h -> whereExpression.and(qSlotSpecEntry.height.eq(h))
        );
        Optional.ofNullable(query.getState()).ifPresent(
                s -> whereExpression.and(qSlotSpecEntry.state.eq(s.name()))
        );
        Optional.ofNullable(query.getType()).ifPresent(
                t -> whereExpression.and(qSlotSpecEntry.slotType.eq(t.name()))
        );
        Optional.ofNullable(query.getImageType()).ifPresent(
                i -> whereExpression.and(qSlotSpecEntry.imageType.contains(i))
        );
        return jpaQueryFactory.selectFrom(qSlotSpecEntry)
                .where(whereExpression)
                .orderBy(qSlotSpecEntry.state.desc(), qSlotSpecEntry.updatedAt.desc())
                .limit(query.getPageSize())
                .offset(query.getPageIndex() - 1)
                .fetchResults();
    }


    @QueryHandler
    private SlotSpecEntry handle(Long specId) {
        return jpaQueryFactory.selectFrom(qSlotSpecEntry)
                .where(qSlotSpecEntry.specId.eq(specId))
                .fetchOne();
    }
}
