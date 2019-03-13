package com.damon.product.core.web.facade.impl;

import com.damon.product.api.dto.req.log.QueryOperateLogReqDTO;
import com.damon.product.api.dto.resp.log.OperateLogRespDTO;
import com.damon.product.api.web.facade.OperateLogFacade;
import com.damon.product.core.query.handler.log.OperateLogTranslator;
import com.damon.product.domain.log.command.FindOperateLogByIdCommand;
import com.damon.product.domain.log.entity.OperateLogEntry;
import com.damon.shared.common.Constants;
import com.damon.shared.common.Pagination;
import com.damon.shared.enums.ResponseCodeEnum;
import com.damon.shared.validation.ArgsValid;
import com.damon.shared.wrapper.ResponseWrapper;
import com.querydsl.core.QueryResults;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

/**
 * 操作日志管理
 * @author Damon S.
 * @version v1.0.1
 * @date 2019年03月12日 09:29
 */
@Slf4j
@Api(tags = "操作日志")
@RestController
@RequiredArgsConstructor
public class OperateLogFacadeImpl implements OperateLogFacade {

    private final QueryGateway queryGateway;
    private final OperateLogTranslator translator;


    @ArgsValid
    @Override
    @ApiOperation(value = "查询操作日志", notes = "查询操作日志")
    public ResponseWrapper<Pagination<OperateLogRespDTO>> query(
            QueryOperateLogReqDTO queryOperateLogReqDTO) {
        CompletableFuture<QueryResults> futureResults = queryGateway.query(
                translator.translateFromReqDTO(queryOperateLogReqDTO), QueryResults.class);

        QueryResults<OperateLogEntry> queryResults;
        try {
            queryResults = (QueryResults<OperateLogEntry>) futureResults.get();
        } catch (Exception e) {
            log.error("查询操作日志信息异常{}", e);
            return new ResponseWrapper<>(ResponseCodeEnum.INTERNAL_ERROR);
        }

        Pagination<OperateLogRespDTO> operateLogRespDTOs = new Pagination<>(
                (queryResults.getOffset() + Constants.INT_ONE),
                queryResults.getLimit(),
                queryResults.getTotal(),
                translator.translateToRespDTOs(queryResults)
        );
        return new ResponseWrapper<>(operateLogRespDTOs);
    }

    @Override
    @ApiOperation(value = "获取指定日志", notes = "获取指定日志")
    public ResponseWrapper<OperateLogRespDTO> find(Long logId) {
        CompletableFuture<OperateLogEntry> futureResult =
                queryGateway.query(new FindOperateLogByIdCommand(logId), OperateLogEntry.class);

        OperateLogEntry foundEntry;
        try {
            foundEntry = futureResult.get();
        } catch (Exception e) {
            log.error("获取指定操作日志信息异常{0}", e);
            return new ResponseWrapper<>(ResponseCodeEnum.INTERNAL_ERROR);
        }
        return new ResponseWrapper<>(
                translator.translateToRespDTO(foundEntry)
        );
    }
}
