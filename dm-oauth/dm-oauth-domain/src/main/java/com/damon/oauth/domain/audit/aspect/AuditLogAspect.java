package com.damon.oauth.domain.audit.aspect;

import com.damon.oauth.domain.audit.aggregate.OperationLog;
import com.damon.shared.anno.AuditOperation;
import com.damon.shared.enums.OperateType;
import com.damon.shared.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * 审计、日志点切面处理类
 * @author Damon S.
 * @version v1.0.1
 * @date 2019年06月09日 17:58
 */
@Slf4j
@Order(30)
//@ConditionalOnProperty(name = "hcloud.log.operate.enable", havingValue = "true")
@Aspect
@Component
public class AuditLogAspect {

    @Value("${hcloud.log.operate.query.enable:false}")
    private boolean queryLogEnabled;


    @Around("@annotation(auditOperation)")
    public Object around(ProceedingJoinPoint point, AuditOperation auditOperation) throws Throwable {
        String type = auditOperation.type();
        if (StringUtils.isEmpty(type)) {
            throw new SystemException("审计注解(AuditOperation)类型(type)值不能为空");
        }

        // 是否需要记录查询日志
        OperateType operateType = OperateType.valueOf(type);
        if (!queryLogEnabled && OperateType.QUERY.equals(operateType)){
            return point.proceed();
        }

        // 以下进行正常审计日志记录
        long beginTime = System.currentTimeMillis();
        Object result = point.proceed();
        long duration = System.currentTimeMillis() - beginTime;

        this.recordAuditLog(OperationLog.builder()
                .operateId(0L)
                .userId("")
                .operate("")
                .ip("")
                .uri("")
                .duration(duration)
                .params(Arrays.toString(point.getArgs()))
                .type("")
                .build());
        return result;
    }

    private void recordAuditLog(OperationLog operationLog) {
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String requestURI = request.getRequestURI();

//            com.hcloud.audit.api.bean.OperateLog operateLogBean = com.hcloud.audit.api.bean.OperateLog.builder().params(params)
//                    .type(type)
//                    .uri(requestURI)
//                    .ip(ApplicationUtils.findRequestIP(request))
//                    .operate(title)
//                    .username(AuthUtil.getUser().getName())
//                    .time(time).build();
//            SpringUtil.publishEvent(new OperateLogEvent(operateLogBean));
        } catch (Exception e) {
            log.error("保存操作日志失败", e);
        }
    }
}
