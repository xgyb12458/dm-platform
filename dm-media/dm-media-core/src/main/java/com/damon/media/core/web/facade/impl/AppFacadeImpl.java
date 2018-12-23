package com.damon.media.core.web.facade.impl;

import com.damon.media.api.dto.req.app.CreateAppReqDTO;
import com.damon.media.api.dto.req.app.QueryAppReqDTO;
import com.damon.media.api.dto.req.app.UpdateAppReqDTO;
import com.damon.media.api.dto.resp.app.AppInfoRespDTO;
import com.damon.media.api.web.facade.AppFacade;
import com.damon.media.core.query.handler.app.AppTranslator;
import com.damon.media.domain.app.aggregate.AppId;
import com.damon.media.domain.app.command.*;
import com.damon.media.domain.app.entity.AppEntry;
import com.damon.media.shared.enums.MediaSource;
import com.damon.shared.common.Constants;
import com.damon.shared.common.Pagination;
import com.damon.shared.enums.OSCategory;
import com.damon.shared.enums.ResponseCodeEnum;
import com.damon.shared.validation.ArgsValid;
import com.damon.shared.wrapper.ResponseWrapper;
import com.google.common.base.Preconditions;
import com.querydsl.core.QueryResults;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;


/**
 * @author Damon S.
 */
@RestController
@Api(tags = "应用管理")
public class AppFacadeImpl implements AppFacade {
    private static final Logger LOGGER = LoggerFactory.getLogger(AppFacadeImpl.class);

    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;
    private final AppTranslator appTranslator;


    public AppFacadeImpl(CommandGateway commandGateway,
                          QueryGateway queryGateway,
                          AppTranslator translator) {
        this.queryGateway = queryGateway;
        this.commandGateway = commandGateway;
        this.appTranslator = translator;
    }


    @ArgsValid
    @Override
    @ApiOperation(value = "查询应用", notes = "获取符合查询条件的应用列表")
    public ResponseWrapper<Pagination<AppInfoRespDTO>> query(QueryAppReqDTO queryAppReqDTO) {
        QueryAppCommand queryAppCommand = QueryAppCommand.builder()
                .name(queryAppReqDTO.getName())
                .os(queryAppReqDTO.getOs())
                .industry(queryAppReqDTO.getIndustry())
                .type(queryAppReqDTO.getType())
                .status(queryAppReqDTO.getStatus())
                .state(queryAppReqDTO.getState())
                .source(Optional.ofNullable(queryAppReqDTO.getSource()).orElse(MediaSource.ALIEN))
                .userId(queryAppReqDTO.getUserId())
                .pageIndex(Optional.ofNullable(queryAppReqDTO.getPageIndex()).orElse(Constants.START_PAGE_INDEX))
                .pageSize(Optional.ofNullable(queryAppReqDTO.getPageSize()).orElse(Constants.DEFAULT_PAGE_SIZE))
                .build();
        CompletableFuture<QueryResults> entriesFuture = queryGateway.query(queryAppCommand, QueryResults.class);

        ResponseWrapper<Pagination<AppInfoRespDTO>> response;
        try {
            QueryResults<AppEntry> appEntries = (QueryResults<AppEntry>)entriesFuture.get();
            response = new ResponseWrapper<>(new Pagination<>(
                    queryAppCommand.getPageIndex(),
                    queryAppCommand.getPageSize(),
                    Long.valueOf(appEntries.getTotal()).intValue(),
                    appTranslator.translateToRespDTOs(appEntries)
            ));
        } catch (Exception e) {
            LOGGER.error("=========查询资源位信息失败：" + e.toString());
            response = new ResponseWrapper<>(
                    ResponseCodeEnum.INTERNAL_ERROR
            );
        }
//        new ResponseEntity<>("", HttpStatus.OK);
        ResponseEntity.ok("");
        return response;
    }


    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "appIds", dataType = "Long", required = true, value="应用Id")
    })
    @Override
    @ApiOperation(value = "获取应用详情", notes = "查看指定ID的应用详情")
    public ResponseWrapper<AppInfoRespDTO> findById(@PathVariable("appId") Long appId) {
        CompletableFuture<AppEntry> entryFuture = queryGateway.query(appId, AppEntry.class);

        ResponseWrapper<AppInfoRespDTO> response;
        try {
            response = new ResponseWrapper<>(
                    appTranslator.translateToRespDTO(entryFuture.get())
            );
        } catch (Exception e) {
            LOGGER.error("=========查询信息流布局样式信息失败：" + e.toString());
            response = new ResponseWrapper<>(
                    ResponseCodeEnum.INTERNAL_ERROR
            );
        }
        return response;
    }


    @ArgsValid
    @Override
    @ApiOperation(value = "新建应用", notes = "提供必要参数，新建应用APP")
    public ResponseWrapper<Long> create(CreateAppReqDTO createAppReqDTO) {
        Long operatorId = 10000L;
        CreateAppCommand command = CreateAppCommand.builder()
                .appId(new AppId())
                .name(createAppReqDTO.getName())
                .type(createAppReqDTO.getType())
                .downloadUrl(createAppReqDTO.getDownloadUrl())
                .packageName(createAppReqDTO.getPackageName())
                .industry(createAppReqDTO.getIndustry())
                .os(Optional.ofNullable(createAppReqDTO.getOs()).orElse(OSCategory.NA))
                .category(Optional.ofNullable(createAppReqDTO.getCategory()).orElse(Constants.NA))
                .snapshot(Optional.ofNullable(createAppReqDTO.getSnapshot()).orElse(Constants.NA))
                .keywords(Optional.ofNullable(createAppReqDTO.getKeywords()).orElse(Constants.NA))
                .description(Optional.ofNullable(createAppReqDTO.getDescription()).orElse(Constants.NA))
                .userId(Optional.ofNullable(createAppReqDTO.getUserId()).orElse(operatorId))
                .createdBy(operatorId)
                .createdAt(Timestamp.from(Instant.now()))
                .build();
        AppId createdAppId = commandGateway.sendAndWait(command);

        if (0 < createdAppId.getValue()) {
            return new ResponseWrapper<>(createdAppId.getValue());
        } else {
            return new ResponseWrapper<>(ResponseCodeEnum.INTERNAL_ERROR);
        }
    }


    @ArgsValid
    @Override
    @ApiOperation(value = "修改应用", notes = "编辑应用APP信息")
    public ResponseWrapper<Boolean> update(UpdateAppReqDTO updateAppReqDTO) {
        Long operatorId = 10000L;
        UpdateAppCommand command = UpdateAppCommand.builder()
                .appId(new AppId(updateAppReqDTO.getAppId()))
                .category(updateAppReqDTO.getCategory())
                .description(updateAppReqDTO.getDescription())
                .industry(updateAppReqDTO.getIndustry())
                .snapshot(updateAppReqDTO.getSnapshot())
                .keywords(updateAppReqDTO.getKeywords())
                .updatedBy(operatorId)
//                .updated(Timestamp.from(Instant.now()))
                .build();
        commandGateway.sendAndWait(command);
        return new ResponseWrapper<>(Boolean.TRUE);
    }


    @Override
    @ApiOperation(value = "启用应用", notes = "启用应用")
    public ResponseWrapper<Boolean> activate(@PathVariable("appId") Long appId) {
        Preconditions.checkNotNull(appId);
        Long operatorId = 10000L;

        commandGateway.sendAndWait(
                new ActivateAppCommand(new AppId(appId), operatorId)
        );
        return new ResponseWrapper<>(Boolean.TRUE);
    }


    @Override
    @ApiOperation(value = "停用应用", notes = "停用应用")
    public ResponseWrapper<Boolean> deactivate(@PathVariable("appId") Long appId) {
        Preconditions.checkNotNull(appId);
        Long operatorId = 10000L;

        commandGateway.sendAndWait(
                new DeactivateAppCommand(new AppId(appId), operatorId)
        );
        return new ResponseWrapper<>(Boolean.TRUE);
    }


    @Override
    @ApiOperation(value = "通过应用审核", notes = "通过应用审核")
    public ResponseWrapper<Boolean> pass(@PathVariable("appId") Long appId) {
        Preconditions.checkNotNull(appId);
        Long operatorId = 10000L;

        commandGateway.sendAndWait(
                new PassAppCommand(new AppId(appId), operatorId)
        );
        return new ResponseWrapper<>(Boolean.TRUE);
    }


    @Override
    @ApiOperation(value = "驳回应用审核", notes = "驳回应用审核")
    public ResponseWrapper<Boolean> reject(@PathVariable("appId") Long appId) {
        Preconditions.checkNotNull(appId);
        Long operatorId = 10000L;

        commandGateway.sendAndWait(
                new RejectAppCommand(new AppId(appId), operatorId)
        );
        return new ResponseWrapper<>(Boolean.TRUE);
    }
}
