package com.damon.oauth.core.query.repository.audit;

import com.damon.oauth.domain.audit.aggregate.OperationLog;
import com.damon.oauth.domain.audit.entity.AuditEntry;
import com.damon.oauth.domain.audit.entity.AuditRepository;
import com.damon.product.domain.brand.event.AuditEvent;
import com.damon.product.domain.brand.event.LoginEvent;
import com.damon.product.domain.brand.event.OperateEvent;
import com.damon.shared.common.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@EnableAsync
@Component
@RequiredArgsConstructor
public class EventLogListener {

    private final AuditRepository auditRepository;


    @Async
    @EventListener(AuditEvent.class)
    public void onAuditLog(AuditEvent event) {
        OperationLog operationLog = (OperationLog) event.getSource();
        AuditEntry auditEntry = AuditEntry.builder()
                .operateId(operationLog.getOperateId())
                .userId(operationLog.getUserId())
                .ip(operationLog.getIp())
                .uri(operationLog.getUri())
                .params(operationLog.getParams())
                .duration(operationLog.getDuration())
                .type(operationLog.getType())
                .operate(operationLog.getOperate())
                .build();

        this.auditRepository.saveAndFlush(auditEntry);
        log.info(Constants.PREFIX_LOG + "========>>日志记录成功：{}.", operationLog);
    }


    @Async
    @EventListener(OperateEvent.class)
    public void onAuditLog(OperateEvent event) {
        Object source = event.getSource();
        log.info(Constants.PREFIX_LOG + "========>>日志记录成功：{}.", source);
    }


    @Async
    @EventListener(LoginEvent.class)
    public void onAuditLog(LoginEvent event) {
        Object source = event.getSource();
        log.info(Constants.PREFIX_LOG + "========>>日志记录成功：{}.", source);
    }
}
