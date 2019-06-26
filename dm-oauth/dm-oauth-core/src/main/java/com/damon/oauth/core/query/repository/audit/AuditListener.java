package com.damon.oauth.core.query.repository.audit;

import com.damon.oauth.domain.audit.aggregate.OperationLog;
import com.damon.oauth.domain.audit.entity.AuditRepository;
import com.damon.oauth.domain.audit.entity.QAuditEntry;
import com.damon.product.domain.brand.event.AuditEvent;
import com.damon.product.domain.brand.event.LoginEvent;
import com.damon.product.domain.brand.event.OperateEvent;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.common.jpa.EntityManagerProvider;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

/**
 * 审计时间监听类
 * @author Damon S.
 * @version v1.0.1
 * @date 2019年06月09日 17:58
 */
@Slf4j
@Component
@EnableAsync
public class AuditListener {

    private final JPAQueryFactory jpaQueryFactory;
    private final AuditRepository auditRepository;
    private final QAuditEntry qAuditEntry;


    public AuditListener(EntityManagerProvider managerProvider,
                         AuditRepository auditRepository) {
        this.jpaQueryFactory = new JPAQueryFactory(
                managerProvider.getEntityManager()
        );
        this.auditRepository = auditRepository;
        this.qAuditEntry = QAuditEntry.auditEntry;
    }


    @Async
    @EventListener(AuditEvent.class)
    public void listenOperateLog(AuditEvent event) {
        OperationLog operationLog = (OperationLog) event.getSource();
        log.info("远程日志记录成功：{}", operationLog);
    }


    @Async
    @EventListener(OperateEvent.class)
    public void listenLoginLog(OperateEvent event) {
        Object source = event.getSource();
//        HCloudResult hepgResult = remoteLogService.saveLoginLog((LoginLog) source);
        log.info("远程日志记录成功：{}", source);
    }


    @Async
    @EventListener(LoginEvent.class)
    public void listenLoginLog(LoginEvent event) {
        Object source = event.getSource();
//        HCloudResult hepgResult = remoteLogService.saveLoginLog((LoginLog) source);
        log.info("远程日志记录成功：{}", source);
    }
}
