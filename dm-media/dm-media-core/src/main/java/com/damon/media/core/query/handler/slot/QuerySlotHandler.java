package com.damon.media.core.query.handler.slot;

import com.damon.media.domain.slot.command.QuerySlotCommand;
import com.damon.media.domain.slot.entity.QSlotEntry;
import com.damon.media.domain.slot.entity.SlotEntry;
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
public class QuerySlotHandler {

    private final JPAQueryFactory jpaQueryFactory;
    private final QSlotEntry qSlotEntry;

    public QuerySlotHandler(EntityManagerProvider managerProvider) {
        this.jpaQueryFactory = new JPAQueryFactory(
                managerProvider.getEntityManager()
        );
        this.qSlotEntry = QSlotEntry.slotEntry;
    }


    @QueryHandler
    private QueryResults<SlotEntry> handle(QuerySlotCommand query) {
        final BooleanBuilder whereExpression = new BooleanBuilder();
        Optional.ofNullable(query.getAppId()).ifPresent(
                a -> whereExpression.and(qSlotEntry.appIds.contains(String.valueOf(a)))
        );
        Optional.ofNullable(query.getName()).ifPresent(
                n -> whereExpression.and(qSlotEntry.name.contains(n))
        );
        Optional.ofNullable(query.getAlias()).ifPresent(
                l -> whereExpression.and(qSlotEntry.alias.eq(l))
        );
        Optional.ofNullable(query.getChannel()).ifPresent(
                c -> whereExpression.and(qSlotEntry.channel.eq(c))
        );
        Optional.ofNullable(query.getSpecId()).ifPresent(
                s -> whereExpression.and(qSlotEntry.specId.eq(s))
        );
        Optional.ofNullable(query.getUserId()).ifPresent(
                u -> whereExpression.and(qSlotEntry.userId.eq(u))
        );
        Optional.ofNullable(query.getState()).ifPresent(
                e -> whereExpression.and(qSlotEntry.state.eq(e.name()))
        );
        Optional.ofNullable(query.getType()).ifPresent(
                t -> whereExpression.and(qSlotEntry.type.eq(t.name()))
        );
        Optional.ofNullable(query.getStatus()).ifPresent(
                i -> whereExpression.and(qSlotEntry.status.eq(i.name()))
        );

        return jpaQueryFactory.selectFrom(qSlotEntry)
                .where(whereExpression)
                .orderBy(qSlotEntry.state.desc(), qSlotEntry.updatedAt.desc())
                .limit(query.getPageSize())
                .offset(query.getPageIndex() - 1)
                .fetchResults();
    }


    @QueryHandler
    private SlotEntry handle(Long slotId) {
        return jpaQueryFactory.selectFrom(qSlotEntry)
                .where(qSlotEntry.slotId.eq(slotId))
                .fetchOne();
    }
}
