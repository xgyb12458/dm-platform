package com.damon.product.core.query.handler.log;

import com.damon.product.api.dto.req.log.QueryOperateLogReqDTO;
import com.damon.product.api.dto.resp.log.OperateLogRespDTO;
import com.damon.product.domain.brand.aggregate.BrandAggregate;
import com.damon.product.domain.brand.event.BrandUpdatedEvent;
import com.damon.product.domain.log.aggregate.OperateContent;
import com.damon.product.domain.log.command.QueryOperateLogCommand;
import com.damon.product.domain.log.entity.OperateLogEntry;
import com.querydsl.core.QueryResults;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 操作日志对象转换工具
 * @author Damon S.
 * @version v1.0.1
 * @date 2019年03月12日 09:18
 */
@Component
public final class OperateLogTranslator {

    /**
     * 转换SPU对象
     */
    private OperateLogRespDTO translateToRespDTO(OperateLogEntry logEntry) {
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

    public List<OperateContent> translateUpdates(BrandAggregate brand, BrandUpdatedEvent event) {
        List<OperateContent> contents = new ArrayList<>();

        OperateContent.OperateContentBuilder builder = OperateContent.builder();
        if (!Objects.equals(brand.getBrandId(), event.getBrandId())) {
            builder.field("brandId").origin(brand.getBrandId()).present(event.getBrandId());
        }
        contents.add(builder.build());

        return contents;
    }
}
