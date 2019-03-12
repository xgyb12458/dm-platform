package com.damon.product.core.query.handler.log;

import com.damon.product.domain.spu.command.QueryOperateLogCommand;
import com.damon.product.domain.spu.entity.QOperateLogEntry;
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
 * 日志查询处理对象
 * @author Damon S.
 * @version v1.0.1
 * @date 2019年03月11日 23:51
 */
@Slf4j
@Component
public class OperateLogQueryHandler {

    private final JPAQueryFactory jpaQueryFactory;
    private final QOperateLogEntry qOperateLogEntry;

    public OperateLogQueryHandler(EntityManagerProvider managerProvider) {
        this.jpaQueryFactory = new JPAQueryFactory(
                managerProvider.getEntityManager()
        );
        this.qOperateLogEntry = QOperateLogEntry.operateLogEntry;
    }


    @SuppressWarnings("UnusedDeclaration")
    @QueryHandler
    private QueryResults handle(QueryOperateLogCommand command) {
        log.trace(Constants.PREFIX_PRODUCT + "=======>handling QueryOperateLogCommand：{}", command);

        final BooleanBuilder expression = new BooleanBuilder();
        // 拼接查询条件
        Optional.ofNullable(command.getTarget()).ifPresent(
                target -> expression.and(qOperateLogEntry.target.eq(target))
        );
        Optional.ofNullable(command.getObjectId()).ifPresent(
                objectId -> expression.and(qOperateLogEntry.objectId.eq(objectId))
        );
        Optional.ofNullable(command.getOperatedBy()).ifPresent(
                operatedBy -> expression.and(qOperateLogEntry.operatedBy.eq(operatedBy))
        );
        Optional.ofNullable(command.getOperatedTo()).ifPresent(
                to -> expression.and(
                        qOperateLogEntry.operatedAt.before(new Timestamp(to))
                )
        );
        Optional.ofNullable(command.getOperatedFrom()).ifPresent(
                from -> expression.and(
                        qOperateLogEntry.operatedAt.after(new Timestamp(from))
                )
        );
        // 执行查询操作
        return this.jpaQueryFactory.selectFrom(qOperateLogEntry)
                .where(expression)
                .orderBy(qOperateLogEntry.operatedAt.desc())
                .limit(command.getPageSize())
                .offset(command.getPageIndex() - Constants.INT_ONE)
                .fetchResults();
    }
}
