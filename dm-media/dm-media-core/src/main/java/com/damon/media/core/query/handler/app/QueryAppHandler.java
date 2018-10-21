package com.damon.media.core.query.handler.app;

import com.damon.media.domain.app.command.QueryAppCommand;
import com.damon.media.domain.app.entity.AppEntry;
import com.damon.media.domain.app.entity.AppEntryRepository;
import com.damon.media.domain.app.entity.QAppEntry;
import com.damon.media.shared.enums.MediaType;
import com.damon.shared.enums.OSCategory;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.axonframework.common.jpa.EntityManagerProvider;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.Optional;

/**
 * 与查询信息流布局样式相关的功能
 * @author Damon S.
 */
@Component
public class QueryAppHandler {

    private final JPAQueryFactory jpaQueryFactory;
    private final QAppEntry qAppEntry;
    private final AppEntryRepository appEntryRepository;

    public QueryAppHandler(EntityManagerProvider managerProvider,
                           AppEntryRepository appEntryRepository) {
        this.jpaQueryFactory = new JPAQueryFactory(
                managerProvider.getEntityManager()
        );
        this.qAppEntry = QAppEntry.appEntry;
        this.appEntryRepository = appEntryRepository;
    }


    @QueryHandler
    private QueryResults<AppEntry> handle(QueryAppCommand query) {
        final BooleanBuilder whereExpression = new BooleanBuilder();
        Optional.ofNullable(query.getName()).ifPresent(
                n -> whereExpression.and(qAppEntry.name.contains(n))
        );
        Optional.ofNullable(query.getOs()).ifPresent(
                o -> whereExpression.and(qAppEntry.os.eq(o.name()))
        );
        Optional.ofNullable(query.getState()).ifPresent(
                s -> whereExpression.and(qAppEntry.state.eq(s.name()))
        );
        Optional.ofNullable(query.getType()).ifPresent(
                t -> whereExpression.and(qAppEntry.type.eq(t.name()))
        );
        Optional.ofNullable(query.getStatus()).ifPresent(
                a -> whereExpression.and(qAppEntry.status.eq(a.name()))
        );
        Optional.ofNullable(query.getIndustry()).ifPresent(
                i -> whereExpression.and(qAppEntry.industry.contains(i))
        );
        Optional.ofNullable(query.getUserId()).ifPresent(
                u -> whereExpression.and(qAppEntry.userId.eq(u))
        );
        return jpaQueryFactory.selectFrom(qAppEntry)
                .where(whereExpression)
                .orderBy(qAppEntry.state.desc(), qAppEntry.updatedAt.desc())
                .limit(query.getPageSize())
                .offset(query.getPageIndex() - 1)
                .fetchResults();
    }


    @QueryHandler
    private AppEntry handle(Long appId) {
        return jpaQueryFactory.selectFrom(qAppEntry)
                .where(qAppEntry.appId.eq(appId))
                .fetchOne();
    }

    public Boolean checkIfExists(@NotNull String name, @NotNull OSCategory os, @NotNull MediaType type) {
        return appEntryRepository.exists(
                qAppEntry.name.eq(name)
                .and(qAppEntry.type.eq(type.name()))
                .and(qAppEntry.os.eq(os.name()))
        );
    }
}
