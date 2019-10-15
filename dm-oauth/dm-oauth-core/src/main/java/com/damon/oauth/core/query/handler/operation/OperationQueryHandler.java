package com.damon.oauth.core.query.handler.operation;

import com.damon.oauth.domain.operation.command.CreateOperationCommand;
import com.damon.oauth.domain.operation.entity.OperationEntry;
import com.damon.oauth.domain.operation.entity.OperationRepository;
import com.damon.oauth.domain.operation.entity.QOperationEntry;
import com.damon.shared.common.Constants;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.common.jpa.EntityManagerProvider;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

/**
 * 操作域命令处理类
 * @author Damon
 */
@Slf4j
@Component
public class OperationQueryHandler {

    private final JPAQueryFactory jpaQueryFactory;
    private final QOperationEntry qOperationEntry;
    private final OperationRepository operationRepository;

    public OperationQueryHandler(EntityManagerProvider managerProvider,
                             OperationRepository operationRepository) {
        this.jpaQueryFactory = new JPAQueryFactory(
                managerProvider.getEntityManager()
        );
        this.operationRepository = operationRepository;
        this.qOperationEntry = QOperationEntry.operationEntry;
    }


    @SuppressWarnings("UnusedDeclaration")
    @QueryHandler
    private Long handle(CreateOperationCommand command) {
        log.trace(Constants.PREFIX_PMS + "=======>handling CreateOperationCommand：{}", command);

        OperationEntry operationEntry = OperationEntry.builder()
                .operationId(command.getOperationId())
//                .name(event.getName())
//                .code(event.getCode())
//                .logo(event.getLogo())
//                .sort(event.getSort())
//                .bigImage(event.getBigImage())
//                .display(event.getDisplay().name())
//                .removed(event.getRemoved().name())
//                .factoryState(event.getFactoryState().name())
//                .firstLetter(event.getFirstLetter())
//                .homepage(event.getHomepage())
//                .description(event.getDescription())
//                .createdBy(event.getCreatedBy())
//                .createdAt(new Timestamp(event.getCreatedAt().toEpochMilli()))
                .build();

//        brandRepository.saveAndFlush(brandEntry);
//        log.info(Constants.PREFIX_PRODUCT + "========>>Brand aggregate[Id:{}, name:'{}'] created by User[Id:{}] at {} is successfully stored-[DB].",
//                event.getBrandId().getValue(), event.getName(), event.getCreatedBy(), event.getCreatedAt());

        return 0L;
    }

//    @SuppressWarnings("UnusedDeclaration")
//    @QueryHandler
//    private BrandEntry handle(FindBrandByIdCommand command) {
//        log.trace(Constants.PREFIX_PRODUCT + "=======>handling FindBrandByIdCommand：{}", command);
//
//        return this.jpaQueryFactory.selectFrom(qBrandEntry)
//                .where(qBrandEntry.brandId.eq(command.getBrandId()))
//                .fetchOne();
//    }
}
