package com.damon.media.core.web.facade.impl;

import com.damon.media.api.dto.req.slot.layout.CreateLayoutReqDTO;
import com.damon.media.api.dto.req.slot.layout.QueryLayoutReqDTO;
import com.damon.media.api.dto.req.slot.layout.UpdateLayoutReqDTO;
import com.damon.media.api.dto.resp.slot.layout.LayoutInfoRespDTO;
import com.damon.media.api.web.facade.LayoutFacade;
import com.damon.media.core.query.handler.layout.LayoutTranslator;
import com.damon.media.domain.slot.layout.aggregate.LayoutId;
import com.damon.media.domain.slot.layout.command.*;
import com.damon.media.domain.slot.layout.entity.FeedLayoutEntry;
import com.damon.shared.common.Constants;
import com.damon.shared.common.Pagination;
import com.damon.shared.enums.ResponseCodeEnum;
import com.damon.shared.enums.SwitchState;
import com.damon.shared.validation.ArgsValid;
import com.damon.shared.wrapper.ResponseWrapper;
import com.google.common.base.Preconditions;
import com.querydsl.core.QueryResults;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;


/**
 * @author Damon S.
 */
@RestController
@Api(tags = "信息流布局管理")
public class LayoutFacadeImpl implements LayoutFacade {
    private static final Logger LOGGER = LoggerFactory.getLogger(LayoutFacadeImpl.class);

    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;
    private final LayoutTranslator layoutTranslator;


    public LayoutFacadeImpl(CommandGateway commandGateway,
                            QueryGateway queryGateway,
                            LayoutTranslator translator) {
        this.queryGateway = queryGateway;
        this.commandGateway = commandGateway;
        this.layoutTranslator = translator;
    }


    @ArgsValid
    @Override
    @ApiOperation(value = "查询信息流布局", notes = "获取符合查询条件的信息流布局列表")
    public ResponseWrapper<Pagination<LayoutInfoRespDTO>> query(QueryLayoutReqDTO queryLayoutReqDTO) {
        QueryLayoutCommand queryLayoutCommand = QueryLayoutCommand.builder()
                .height(queryLayoutReqDTO.getHeight())
                .width(queryLayoutReqDTO.getWidth())
                .state(queryLayoutReqDTO.getState())
                .type(queryLayoutReqDTO.getLayoutType())
                .pageIndex(
                        Optional.ofNullable(queryLayoutReqDTO.getPageIndex())
                                .orElse(Constants.START_PAGE_INDEX)
                )
                .pageSize(
                        Optional.ofNullable(queryLayoutReqDTO.getPageSize())
                                .orElse(Constants.DEFAULT_PAGE_SIZE)
                )
                .build();
        CompletableFuture<QueryResults> entriesFuture = queryGateway.query(queryLayoutCommand, QueryResults.class);

        ResponseWrapper<Pagination<LayoutInfoRespDTO>> response;
        try {
            QueryResults<FeedLayoutEntry> layoutEntries = (QueryResults<FeedLayoutEntry>)entriesFuture.get();
            response = new ResponseWrapper<>(new Pagination<>(
                    queryLayoutCommand.getPageIndex(),
                    queryLayoutCommand.getPageSize(),
                    Long.valueOf(layoutEntries.getTotal()).intValue(),
                    layoutTranslator.translateToRespDTOs(layoutEntries)
            ));
        } catch (Exception e) {
            LOGGER.error("=========>>查询信息流布局样式信息失败：" + e.toString());
            response = new ResponseWrapper<>(
                    ResponseCodeEnum.INTERNAL_ERROR
            );
        }
        return response;
    }


    @Override
    @ApiOperation(value = "查询信息流布局详情", notes = "根据信息流样式ID获取布局详情")
    public ResponseWrapper<LayoutInfoRespDTO> findLayoutById(@PathVariable(value = "layoutId") Long layoutId) {
        CompletableFuture<FeedLayoutEntry> entryFuture = queryGateway.query(layoutId, FeedLayoutEntry.class);

        ResponseWrapper<LayoutInfoRespDTO> response;
        try {
            response = new ResponseWrapper<>(
                    layoutTranslator.translateToRespDTO(entryFuture.get())
            );
        } catch (Exception e) {
            LOGGER.error("=========>>查询信息流布局样式信息失败：" + e.toString());
            response = new ResponseWrapper<>(
                    ResponseCodeEnum.INTERNAL_ERROR
            );
        }
        return response;
    }


    @ArgsValid
    @Override
    @ApiOperation(value = "新建信息流布局", notes = "提供必要参数，新建信息流布局")
    public ResponseWrapper<Long> create(CreateLayoutReqDTO createLayoutReqDTO) {
        Long operatorId = 10000L;
        CreateLayoutCommand createLayoutCommand = CreateLayoutCommand.builder()
                .layoutId(new LayoutId())
                .width(createLayoutReqDTO.getWidth())
                .height(createLayoutReqDTO.getHeight())
                .state(SwitchState.ON)
                .type(createLayoutReqDTO.getLayoutType())
                .snapshot(Optional.ofNullable(createLayoutReqDTO.getSnapshot()).orElse(Constants.NA))
                .createdBy(operatorId)
                .build();
        LayoutId layoutId = commandGateway.sendAndWait(createLayoutCommand);

        return new ResponseWrapper<>(layoutId.getValue());
    }


    @ArgsValid
    @Override
    @ApiOperation(value = "修改信息流布局", notes = "修改信息流布局")
    public ResponseWrapper<Boolean> update(UpdateLayoutReqDTO updateLayoutReqDTO) {
        Long operatorId = 10000L;
        UpdateLayoutCommand updateLayoutCommand = UpdateLayoutCommand.builder()
                .layoutId(new LayoutId(updateLayoutReqDTO.getLayoutId()))
                .width(updateLayoutReqDTO.getWidth())
                .height(updateLayoutReqDTO.getHeight())
                .snapshot(updateLayoutReqDTO.getSnapshot())
                .updatedBy(operatorId)
                .build();
        commandGateway.sendAndWait(updateLayoutCommand);
        return new ResponseWrapper<>(Boolean.TRUE);
    }


    @Override
    @ApiOperation(value = "启用信息流布局", notes = "启用信息流布局")
    public ResponseWrapper<Boolean> activate(@PathVariable(value = "layoutId") Long layoutId) {
        Preconditions.checkNotNull(layoutId);
        Long operatorId = 10000L;

        commandGateway.sendAndWait(
                new ActivateLayoutCommand(new LayoutId(layoutId), operatorId)
        );
        return new ResponseWrapper<>(Boolean.TRUE);
    }


    @Override
    @ApiOperation(value = "停用信息流布局", notes = "停用信息流布局")
    public ResponseWrapper<Boolean> deactivate(@PathVariable(value = "layoutId") Long layoutId) {
        Preconditions.checkNotNull(layoutId);
        Long operatorId = 10000L;

        commandGateway.sendAndWait(
                new DeactivateLayoutCommand(new LayoutId(layoutId), operatorId)
        );
        return new ResponseWrapper<>(Boolean.TRUE);
    }
}
