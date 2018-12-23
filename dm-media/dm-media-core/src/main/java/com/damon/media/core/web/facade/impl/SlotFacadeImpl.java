package com.damon.media.core.web.facade.impl;

import com.damon.media.api.dto.req.slot.CreateSlotReqDTO;
import com.damon.media.api.dto.req.slot.QuerySlotReqDTO;
import com.damon.media.api.dto.req.slot.UpdateSlotReqDTO;
import com.damon.media.api.dto.resp.slot.SlotInfoRespDTO;
import com.damon.media.api.web.facade.SlotFacade;
import com.damon.media.core.query.handler.slot.SlotTranslator;
import com.damon.media.domain.slot.aggregate.SlotId;
import com.damon.media.domain.slot.command.*;
import com.damon.media.domain.slot.entity.SlotEntry;
import com.damon.shared.common.Constants;
import com.damon.shared.common.Pagination;
import com.damon.shared.enums.ResponseCodeEnum;
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

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * @author Damon S.
 */
@RestController
@Api(tags = "资源位管理")
public class SlotFacadeImpl implements SlotFacade {
    private static final Logger LOGGER = LoggerFactory.getLogger(SlotFacadeImpl.class);

    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;
    private final SlotTranslator slotTranslator;


    public SlotFacadeImpl(CommandGateway commandGateway,
                          QueryGateway queryGateway,
                          SlotTranslator translator) {
        this.queryGateway = queryGateway;
        this.commandGateway = commandGateway;
        this.slotTranslator = translator;
    }


    @ArgsValid
    @Override
    @ApiOperation(value = "资源位列表", notes = "获取符合查询条件的资源位列表")
    public ResponseWrapper<Pagination<SlotInfoRespDTO>> query(QuerySlotReqDTO querySlotReqDTO) {
        QuerySlotCommand querySlotCommand = QuerySlotCommand.builder()
                .appId(querySlotReqDTO.getAppId())
                .name(querySlotReqDTO.getName())
                .alias(querySlotReqDTO.getAlias())
                .os(querySlotReqDTO.getOs())
                .specId(querySlotReqDTO.getSpecId())
                .channel(querySlotReqDTO.getChannel())
                .userId(querySlotReqDTO.getUserId())
                .type(querySlotReqDTO.getType())
                .state(querySlotReqDTO.getState())
                .status(querySlotReqDTO.getStatus())
                .pageIndex(Optional.ofNullable(querySlotReqDTO.getPageIndex()).orElse(Constants.START_PAGE_INDEX))
                .pageSize(Optional.ofNullable(querySlotReqDTO.getPageSize()).orElse(Constants.DEFAULT_PAGE_SIZE))
                .build();
        CompletableFuture<QueryResults> entriesFuture = queryGateway.query(querySlotCommand, QueryResults.class);

        ResponseWrapper<Pagination<SlotInfoRespDTO>> response;
        try {
            QueryResults<SlotEntry> slotEntries = (QueryResults<SlotEntry>)entriesFuture.get();
            response = new ResponseWrapper<>(new Pagination<>(
                    querySlotCommand.getPageIndex(),
                    querySlotCommand.getPageSize(),
                    Long.valueOf(slotEntries.getTotal()).intValue(),
                    slotTranslator.translateToRespDTOs(slotEntries)
            ));
        } catch (Exception e) {
            LOGGER.error("=========查询资源位信息失败：" + e.toString());
            response = new ResponseWrapper<>(
                    ResponseCodeEnum.INTERNAL_ERROR
            );
        }
        return response;
    }

    @ArgsValid
    @Override
    @ApiOperation(value = "查询资源位详情", notes = "根据资源位ID获取资源位详情")
    public ResponseWrapper<SlotInfoRespDTO> findById(@PathVariable("slotId") Long slotId) {
        CompletableFuture<SlotEntry> entryFuture = queryGateway.query(slotId, SlotEntry.class);

        ResponseWrapper<SlotInfoRespDTO> response;
        try {
            response = new ResponseWrapper<>(
                    slotTranslator.translateToRespDTO(entryFuture.get())
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
    @ApiOperation(value = "新建资源位", notes = "提供必要参数，新建资源位")
    public ResponseWrapper<Long> create(CreateSlotReqDTO createDTO) {
        Long operatorId = 10000L;
        CreateSlotCommand createSlotCommand = CreateSlotCommand.builder()
                .slotId(new SlotId())
                .appIds(createDTO.getAppIds())
                .specId(createDTO.getSpecId())
                .os(createDTO.getOs())
                .name(createDTO.getName())
                .type(createDTO.getType())
                .channel(Optional.ofNullable(createDTO.getChannel()).orElse(Constants.NA))
                .snapshot(Optional.ofNullable(createDTO.getSnapshot()).orElse(Constants.NA))
                .blockIndustry(Optional.ofNullable(createDTO.getBlockIndustry()).orElse(Constants.NA))
                .description(Optional.ofNullable(createDTO.getDescription()).orElse(Constants.NA))
                .userId(Optional.ofNullable(createDTO.getUserId()).orElse(operatorId))
                .createdBy(operatorId)
//                .createdAt(Timestamp.from(Instant.now()))
                .build();
        SlotId slotId = commandGateway.sendAndWait(createSlotCommand);
        return new ResponseWrapper<>(slotId.getValue());
    }

    @ArgsValid
    @Override
    @ApiOperation(value = "修改资源位", notes = "提供必要参数，更新资源位信息")
    public ResponseWrapper<Boolean> update(UpdateSlotReqDTO updateDTO) {
        Long operatorId = 10000L;
        UpdateSlotCommand updateSlotCommand = UpdateSlotCommand.builder()
                .slotId(new SlotId(updateDTO.getSlotId()))
                .channel(updateDTO.getChannel())
                .os(updateDTO.getOs())
                .snapshot(updateDTO.getSnapshot())
                .blockIndustry(updateDTO.getBlockIndustry())
                .description(updateDTO.getDescription())
                .specId(updateDTO.getSpecId())
                .updatedBy(operatorId)
                .updateTime(Timestamp.from(Instant.now()))
                .build();
        commandGateway.sendAndWait(updateSlotCommand);
        return new ResponseWrapper<>(Boolean.TRUE);
    }

    @Override
    @ApiOperation(value = "启用资源位", notes = "启用资源位")
    public ResponseWrapper<Boolean> activate(@PathVariable("slotId") Long slotId) {
        Preconditions.checkNotNull(slotId);
        Long operatorId = 10000L;

        commandGateway.sendAndWait(
                new ActivateSlotCommand(new SlotId(slotId), operatorId)
        );
        return new ResponseWrapper<>(Boolean.TRUE);
    }

    @Override
    @ApiOperation(value = "停用资源位", notes = "停用资源位")
    public ResponseWrapper<Boolean> deactivate(@PathVariable("slotId") Long slotId) {
        Preconditions.checkNotNull(slotId);
        Long operatorId = 10000L;

        commandGateway.sendAndWait(
                new DeactivateSlotCommand(new SlotId(slotId), operatorId)
        );
        return new ResponseWrapper<>(Boolean.TRUE);
    }

    @Override
    @ApiOperation(value = "通过资源位审核", notes = "通过资源位审核")
    public ResponseWrapper<Boolean> pass(@PathVariable("slotId") Long slotId) {
        Preconditions.checkNotNull(slotId);
        Long operatorId = 10000L;

        commandGateway.sendAndWait(
                new PassSlotCommand(new SlotId(slotId), operatorId)
        );
        return new ResponseWrapper<>(Boolean.TRUE);
    }

    @Override
    @ApiOperation(value = "驳回资源位审核", notes = "驳回资源位审核")
    public ResponseWrapper<Boolean> reject(@PathVariable("slotId") Long slotId) {
        Preconditions.checkNotNull(slotId);
        Long operatorId = 10000L;

        commandGateway.sendAndWait(
                new RejectSlotCommand(new SlotId(slotId), operatorId)
        );
        return new ResponseWrapper<>(Boolean.TRUE);
    }
}
