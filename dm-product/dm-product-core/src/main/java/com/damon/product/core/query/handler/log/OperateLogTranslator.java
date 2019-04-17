package com.damon.product.core.query.handler.log;

import com.damon.product.api.dto.req.log.QueryOperateLogReqDTO;
import com.damon.product.api.dto.resp.log.OperateLogRespDTO;
import com.damon.product.domain.brand.event.BrandCreatedEvent;
import com.damon.product.domain.brand.event.BrandUpdatedEvent;
import com.damon.product.domain.log.command.QueryOperateLogCommand;
import com.damon.product.domain.log.entity.OperateLogEntry;
import com.damon.product.domain.log.event.OperateLogEvent;
import com.damon.shared.enums.OperateType;
import com.querydsl.core.QueryResults;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.GenericEventMessage;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 操作日志对象转换工具
 * @author Damon S.
 * @version v1.0.1
 * @date 2019年03月12日 09:18
 */
@Component
@RequiredArgsConstructor
public final class OperateLogTranslator {

    private final EventBus eventBus;


    /**
     * 转换SPU对象
     */
    public OperateLogRespDTO translateToRespDTO(OperateLogEntry logEntry) {
        OperateLogRespDTO operateLogRespDTO = new OperateLogRespDTO();
        BeanUtils.copyProperties(logEntry, operateLogRespDTO);

        // 处理其它未自动赋值的字段
        operateLogRespDTO.setOperatedAt(logEntry.getOperatedAt().getTime());
        return operateLogRespDTO;
    }

    /**
     * 转换SPU对象列表
     */
    public List<OperateLogRespDTO> translateToRespDTOs(QueryResults<OperateLogEntry> logEntries) {
        List<OperateLogRespDTO> operateLogRespDTOs =
                new ArrayList<>(logEntries.getResults().size());
        logEntries.getResults().forEach(
                entry -> operateLogRespDTOs.add(translateToRespDTO(entry))
        );
        return operateLogRespDTOs;
    }

    /**
     * 转换为查询命令
     */
    public QueryOperateLogCommand translateFromReqDTO(QueryOperateLogReqDTO reqDTO) {
        return QueryOperateLogCommand.builder()
                .target(reqDTO.getTarget())
                .objectId(reqDTO.getObjectId())
                .operatedBy(reqDTO.getOperatedBy())
                .operatedFrom(reqDTO.getOperatedFrom())
                .operatedTo(reqDTO.getOperatedTo())
                .pageSize(reqDTO.getPageSize())
                .pageIndex(reqDTO.getPageIndex())
                .build();
    }


    /**
     * 发布操作日志事件
     */
    public void logCreateOperation(BrandCreatedEvent event) {
        eventBus.publish(new GenericEventMessage<>(
                OperateLogEvent.builder()
                        .target("BRAND")
                        .objectId(event.getBrandId().getValue())
                        .type(OperateType.CREATE)
                        .content(event)
                        .operatedBy(event.getCreatedBy())
                        .operatedAt(event.getCreatedAt().toEpochMilli())
                        .build()));
    }

    /**
     * 发布操作日志事件
     */
    public void logUpdateOperation(BrandUpdatedEvent event) {
        eventBus.publish(new GenericEventMessage<>(
                OperateLogEvent.builder()
                        .target("BRAND")
                        .objectId(event.getBrandId().getValue())
                        .type(OperateType.UPDATE)
                        .content(event)
                        .operatedBy(event.getUpdatedBy())
                        .operatedAt(event.getUpdatedAt().toEpochMilli())
                        .build()));
    }

//    public void getMethod(Object obj){
//        Class clazz = obj.getClass();
//        Field[] fields = obj.getClass().getDeclaredFields();
//        //获得Object对象中的所有方法
//        Arrays.asList(fields).forEach(
//                field -> {
//                    PropertyDescriptor pd = new PropertyDescriptor(field.getName(), clazz);
//                    Method getMethod = pd.getReadMethod();//获得get方法
//                    getMethod.invoke(obj);//此处为执行该Object对象的get方法
//                    Method setMethod = pd.getWriteMethod();//获得set方法
//                    //此处为执行该Object对象的set方法
//                    setMethod.invoke(obj,"参数");
//                }
//        );
//    }
}
