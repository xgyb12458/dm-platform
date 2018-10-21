package com.damon.media.core.facade.impl;

import com.damon.media.api.dto.req.slot.spec.CreateSpecReqDTO;
import com.damon.media.api.dto.req.slot.spec.QuerySpecReqDTO;
import com.damon.media.api.dto.req.slot.spec.UpdateSpecReqDTO;
import com.damon.media.api.dto.resp.slot.spec.SpecInfoRespDTO;
import com.damon.media.api.web.facade.SpecFacade;
import com.damon.media.core.query.handler.spec.SpecTranslator;
import com.damon.media.domain.slot.spec.aggregate.SpecId;
import com.damon.media.domain.slot.spec.command.*;
import com.damon.media.domain.slot.spec.entity.SlotSpecEntry;
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
import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * @author Damon S.
 */
@RestController
@Api(tags = "资源位规格管理")
public class SpecFacadeImpl implements SpecFacade {
    private static final Logger LOGGER = LoggerFactory.getLogger(SpecFacadeImpl.class);

    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;
    private final SpecTranslator specTranslator;


    public SpecFacadeImpl(CommandGateway commandGateway,
                          QueryGateway queryGateway,
                          SpecTranslator translator) {
        this.queryGateway = queryGateway;
        this.commandGateway = commandGateway;
        this.specTranslator = translator;
    }

    @ArgsValid
    @Override
    @ApiOperation(value = "查询资源位规格", notes = "获取符合查询条件的资源位规格列表")
    public ResponseWrapper<Pagination<SpecInfoRespDTO>> query(QuerySpecReqDTO querySpecReqDTO) {
        QuerySpecCommand querySpecCommand = QuerySpecCommand.builder()
                .height(querySpecReqDTO.getHeight())
                .width(querySpecReqDTO.getWidth())
                .state(querySpecReqDTO.getState())
                .type(querySpecReqDTO.getSlotType())
                .imageType(querySpecReqDTO.getImageType())
                .pageIndex(Optional.ofNullable(querySpecReqDTO.getPageIndex()).orElse(Constants.START_PAGE_INDEX))
                .pageSize(Optional.ofNullable(querySpecReqDTO.getPageSize()).orElse(Constants.DEFAULT_PAGE_SIZE))
                .build();
        CompletableFuture<QueryResults> entriesFuture = queryGateway.query(querySpecCommand, QueryResults.class);

        ResponseWrapper<Pagination<SpecInfoRespDTO>> response;
        try {
            QueryResults<SlotSpecEntry> specEntries = (QueryResults<SlotSpecEntry>)entriesFuture.get();
            response = new ResponseWrapper<>(new Pagination<>(
                    querySpecCommand.getPageIndex(),
                    querySpecCommand.getPageSize(),
                    Long.valueOf(specEntries.getTotal()).intValue(),
                    specTranslator.translateToRespDTOs(specEntries)
            ));
        } catch (Exception e) {
            LOGGER.error("=========查询信息流布局样式信息失败：" + e.toString());
            response = new ResponseWrapper<>(
                    ResponseCodeEnum.INTERNAL_ERROR
            );
        }
        return response;
    }

    @Override
    @ApiOperation(value = "查询资源位规格详情", notes = "根据资源位规格ID获取规格详情")
    public ResponseWrapper<SpecInfoRespDTO> findById(@PathVariable(value = "specId") Long specId) {
        CompletableFuture<SlotSpecEntry> entryFuture = queryGateway.query(specId, SlotSpecEntry.class);

        ResponseWrapper<SpecInfoRespDTO> response;
        try {
            SlotSpecEntry specEntry = entryFuture.get();
            response = new ResponseWrapper<>(
                    specTranslator.translateToRespDTO(specEntry)
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
    @ApiOperation(value = "新建资源位规格", notes = "提供必要参数，新建资源位规格")
    public ResponseWrapper<Long> create(CreateSpecReqDTO createSpecReqDTO) {
        Long operatorId = 10000L;
        CreateSpecCommand createSpecCommand = CreateSpecCommand.builder()
                .specId(new SpecId())
                .width(createSpecReqDTO.getWidth())
                .height(createSpecReqDTO.getHeight())
                .imageType(createSpecReqDTO.getImageType())
                .imageSize(createSpecReqDTO.getImageSize())
                .slotType(createSpecReqDTO.getSlotType())
                .layoutIds(Optional.ofNullable(createSpecReqDTO.getLayoutIds()).orElse(new ArrayList<>()))
                .snapshot(Optional.ofNullable(createSpecReqDTO.getSnapshot()).orElse(Constants.NA))
                .frameCount(Optional.ofNullable(createSpecReqDTO.getFrameCount()).orElse(Constants.INT_ZERO))
                .lookAndFeel(Optional.ofNullable(createSpecReqDTO.getLookAndFeel()).orElse(Constants.EMPTY))
                .createdBy(operatorId)
                .createdAt(Timestamp.from(Instant.now()))
                .build();
        SpecId specId = commandGateway.sendAndWait(createSpecCommand);
        return new ResponseWrapper<>(specId.getValue());
    }

    @ArgsValid
    @Override
    @ApiOperation(value = "修改资源位规格", notes = "修改资源位规格")
    public ResponseWrapper<Boolean> update(UpdateSpecReqDTO updateSpecReqDTO) {
        Long operatorId = 10000L;
        UpdateSpecCommand updateSpecCommand = UpdateSpecCommand.builder()
                .specId(new SpecId(updateSpecReqDTO.getSpecId()))
                .width(updateSpecReqDTO.getWidth())
                .height(updateSpecReqDTO.getHeight())
                .imageType(updateSpecReqDTO.getImageType())
                .imageSize(updateSpecReqDTO.getImageSize())
                .layoutIds(Optional.ofNullable(updateSpecReqDTO.getLayoutIds()).orElse(new ArrayList<>()))
                .snapshot(Optional.ofNullable(updateSpecReqDTO.getSnapshot()).orElse(Constants.NA))
                .frameCount(Optional.ofNullable(updateSpecReqDTO.getFrameCount()).orElse(Constants.INT_ZERO))
                .lookAndFeel(Optional.ofNullable(updateSpecReqDTO.getLookAndFeel()).orElse(Constants.EMPTY))
                .updatedBy(operatorId)
                .updatedAt(Timestamp.from(Instant.now()))
                .build();
        commandGateway.sendAndWait(updateSpecCommand);
        return new ResponseWrapper<>(Boolean.TRUE);
    }

    @Override
    @ApiOperation(value = "启用资源位规格", notes = "启用资源位规格")
    public ResponseWrapper<Boolean> activate(@PathVariable(value = "specId") Long specId) {
        Preconditions.checkNotNull(specId);
        Long operatorId = 10000L;

        commandGateway.sendAndWait(
                new ActivateSpecCommand(new SpecId(specId), operatorId)
        );
        return new ResponseWrapper<>(Boolean.TRUE);
    }

    @Override
    @ApiOperation(value = "停用资源位规格", notes = "停用资源位规格")
    public ResponseWrapper<Boolean> deactivate(@PathVariable(value = "specId") Long specId) {
        Preconditions.checkNotNull(specId);
        Long operatorId = 10000L;

        commandGateway.sendAndWait(
                new DeactivateSpecCommand(new SpecId(specId), operatorId)
        );
        return new ResponseWrapper<>(Boolean.TRUE);
    }
}
