package com.damon.product.core.query.repository.log;

import com.damon.product.domain.log.event.OperateLogEvent;
import com.damon.product.domain.spu.entity.OperateLogEntry;
import com.damon.product.domain.spu.entity.OperateLogRepository;
import com.damon.shared.common.Constants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

/**
 * 操作日志事件侦听器
 * @author Damon S.
 * @version v1.0.1
 * @date 2019年03月10日 21:08
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class OperateLogEventListener {

    private static ObjectMapper mapper = new ObjectMapper();
    private final OperateLogRepository operateLogRepository;

    private final static String CONTENT_DEFAULT = "操作内容序列化失败";


    @SuppressWarnings("UnusedDeclaration")
    @EventHandler
    private void on(OperateLogEvent event) {
        log.trace(Constants.PREFIX_PRODUCT + "========>>handling OperateLogEvent operate log, parameters：{}", event.toString());

        String content = CONTENT_DEFAULT;
        try {
            content = mapper.writeValueAsString(
                    event.getContent()
            );
        } catch (JsonProcessingException e) {
            log.error("========>>parsing OperateLogEvent object failed. {}", e);
        }

        OperateLogEntry logEntry = OperateLogEntry.builder()
                .source(event.getSource())
                .objectId(event.getObjectId())
                .type(event.getType())
                .content(content)
                .operatedBy(event.getOperatedBy())
                .operatedAt(new Timestamp(event.getOperatedAt()))
                .build();
        // 写入日志表
        operateLogRepository.saveAndFlush(logEntry);
    }
}
