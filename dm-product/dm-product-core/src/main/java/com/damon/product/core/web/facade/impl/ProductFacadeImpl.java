package com.damon.product.core.web.facade.impl;

import com.damon.product.api.dto.req.spu.CreateSpuReqDTO;
import com.damon.product.api.dto.req.spu.QuerySpuReqDTO;
import com.damon.product.api.dto.req.spu.UpdateSpuReqDTO;
import com.damon.product.api.dto.resp.spu.CreateSpuRespDTO;
import com.damon.product.api.dto.resp.spu.SpuInfoRespDTO;
import com.damon.product.api.web.facade.ProductFacade;
import com.damon.product.core.query.handler.spu.SpuAdapter;
import com.damon.product.domain.spu.aggregate.SpuId;
import com.damon.product.domain.spu.command.*;
import com.damon.shared.validation.ArgsValid;
import com.damon.shared.wrapper.ResponseWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品管理接口
 * @author Damon S.
 */
@Api(tags = "商品管理接口")
@RestController
@RequiredArgsConstructor
public class ProductFacadeImpl implements ProductFacade {

    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;


    @ArgsValid
    @Override
    @ApiOperation(value = "创建商品", notes = "创建商品参数")
    public ResponseWrapper<CreateSpuRespDTO> create(
            CreateSpuReqDTO createSpuReqDTO) {
        commandGateway.sendAndWait(
                SpuAdapter.transformCommand(createSpuReqDTO)
        );
        CreateSpuRespDTO createSpuRespDTO = new CreateSpuRespDTO();

        return new ResponseWrapper<>(createSpuRespDTO);
    }

    @ArgsValid
    @Override
    @ApiOperation(value = "查询商品", notes = "按条件查询商品信息")
    public ResponseWrapper<List<SpuInfoRespDTO>> query(QuerySpuReqDTO querySpuReqDTO) {
        QuerySpuCommand command = QuerySpuCommand.builder()
                .build();

        queryGateway.query(command, List.class);
        List<SpuInfoRespDTO> spuInfoRespDTOs = new ArrayList<>();

        return new ResponseWrapper<>(spuInfoRespDTOs);
    }

    @ArgsValid
    @Override
    @ApiOperation(value = "更新商品", notes = "编辑商品信息")
    public ResponseWrapper<Boolean> update(UpdateSpuReqDTO updateSpuReqDTO) {
        UpdateSpuCommand command = UpdateSpuCommand.builder()
                .build();

        queryGateway.query(command, List.class);
        return new ResponseWrapper<>();
    }

    @Override
    @ApiOperation(value = "获取商品", notes = "获取指定商品信息")
    public ResponseWrapper<SpuInfoRespDTO> find(Long spuId) {
        QuerySpuCommand command = QuerySpuCommand.builder()
                .spuId(new SpuId(spuId))
                .build();

        commandGateway.sendAndWait(command);
        SpuInfoRespDTO spuInfoRespDTO = new SpuInfoRespDTO();

        return new ResponseWrapper<>(spuInfoRespDTO);
    }

    @Override
    @ApiOperation(value = "删除商品", notes = "删除商品（逻辑删除）")
    public ResponseWrapper<Boolean> remove(Long spuId) {
        final Long currentUserId = 1L;
        RemoveSpuCommand command = new RemoveSpuCommand(new SpuId(spuId), currentUserId);

        commandGateway.sendAndWait(command);
        return new ResponseWrapper<>();
    }

    @Override
    @ApiOperation(value = "恢复商品", notes = "恢复删除的商品")
    public ResponseWrapper<Boolean> recover(Long spuId) {
        final Long currentUserId = 1L;
        RecoverSpuCommand command = new RecoverSpuCommand(new SpuId(spuId), currentUserId);

        commandGateway.sendAndWait(command);
        return new ResponseWrapper<>();
    }

    @Override
    @ApiOperation(value = "变更新品标识", notes = "变更新品标识")
    public ResponseWrapper<Boolean> changeNewProductState(Long spuId) {
        final Long currentUserId = 1L;
        ChangeSpuNewProductCommand command = new ChangeSpuNewProductCommand(new SpuId(spuId), currentUserId);

        commandGateway.sendAndWait(command);
        return new ResponseWrapper<>();
    }

    @Override
    @ApiOperation(value = "变更推荐标识", notes = "变更推荐标识")
    public ResponseWrapper<Boolean> changeRecommendState(Long spuId) {
        final Long currentUserId = 1L;
        ChangeSpuRecommendedCommand command = new ChangeSpuRecommendedCommand(new SpuId(spuId), currentUserId);

        commandGateway.sendAndWait(command);
        return new ResponseWrapper<>();
    }

    @Override
    @ApiOperation(value = "变更售罄标识", notes = "变更售罄标识")
    public ResponseWrapper<Boolean> changeSoldOutState(Long spuId) {
        final Long currentUserId = 1L;
        ChangeSpuSoldOutCommand command = new ChangeSpuSoldOutCommand(new SpuId(spuId), currentUserId);

        commandGateway.sendAndWait(command);
        return new ResponseWrapper<>();
    }

    @Override
    @ApiOperation(value = "变更退货标识", notes = "变更退货标识")
    public ResponseWrapper<Boolean> changeCanReturnState(Long spuId) {
        final Long currentUserId = 1L;
        ChangeSpuCanReturnCommand command = new ChangeSpuCanReturnCommand(new SpuId(spuId), currentUserId);

        commandGateway.sendAndWait(command);
        return new ResponseWrapper<>();
    }

    @Override
    @ApiOperation(value = "重置审核状态", notes = "重置审核状态至草稿状态")
    public ResponseWrapper<Boolean> resetVerifyState(Long spuId) {
        final Long currentUserId = 1L;
        ResetSpuVerificationCommand command = new ResetSpuVerificationCommand(new SpuId(spuId), currentUserId);

        commandGateway.sendAndWait(command);
        return new ResponseWrapper<>();
    }

    @Override
    @ApiOperation(value = "提交审核", notes = "提交审核")
    public ResponseWrapper<Boolean> commitVerification(Long spuId) {
        final Long currentUserId = 1L;
        CommitSpuVerificationCommand command = new CommitSpuVerificationCommand(new SpuId(spuId), currentUserId);

        commandGateway.sendAndWait(command);
        return new ResponseWrapper<>();
    }

    @Override
    @ApiOperation(value = "通过审核", notes = "通过审核")
    public ResponseWrapper<Boolean> approveSpu(Long spuId) {
        final Long currentUserId = 1L;
        ApproveSpuCommand command = new ApproveSpuCommand(new SpuId(spuId), currentUserId);

        commandGateway.sendAndWait(command);
        return new ResponseWrapper<>();
    }

    @Override
    @ApiOperation(value = "驳回审核", notes = "驳回审核")
    public ResponseWrapper<Boolean> rejectSpu(Long spuId) {
        final Long currentUserId = 1L;
        RejectSpuCommand command = new RejectSpuCommand(new SpuId(spuId), currentUserId);

        commandGateway.sendAndWait(command);
        return new ResponseWrapper<>();
    }

    @Override
    @ApiOperation(value = "批量删除", notes = "批量删除SPU")
    public ResponseWrapper<Boolean> batchRemove(List<Long> spuIds) {
        if (CollectionUtils.isEmpty(spuIds)) {
            return new ResponseWrapper<>(Boolean.TRUE);
        }

        final long currentUserId = 1L;
        spuIds.forEach(spuId -> {
                commandGateway.sendAndWait(new RemoveSpuCommand(new SpuId(spuId), currentUserId));
                System.out.println();
            }
        );
        return new ResponseWrapper<>();
    }

    @Override
    @ApiOperation(value = "批量变更新品状态", notes = "批量变更新品状态")
    public ResponseWrapper<Boolean> batchChangeNewProductState(List<Long> spuIds) {
        if (CollectionUtils.isEmpty(spuIds)) {
            return new ResponseWrapper<>(Boolean.TRUE);
        }

        final long currentUserId = 1L;
        spuIds.forEach(
                spuId -> commandGateway.sendAndWait(new ChangeSpuNewProductCommand(new SpuId(spuId), currentUserId))
        );
        return new ResponseWrapper<>();
    }

    @Override
    @ApiOperation(value = "批量变更推荐状态", notes = "批量变更推荐状态")
    public ResponseWrapper<Boolean> batchChangeRecommendState(List<Long> spuIds) {
        if (CollectionUtils.isEmpty(spuIds)) {
            return new ResponseWrapper<>(Boolean.TRUE);
        }

        final long currentUserId = 1L;
        spuIds.forEach(
                spuId -> commandGateway.sendAndWait(new ChangeSpuRecommendedCommand(new SpuId(spuId), currentUserId))
        );
        return new ResponseWrapper<>();
    }

    @Override
    @ApiOperation(value = "批量变更售罄状态", notes = "批量变更售罄状态")
    public ResponseWrapper<Boolean> batchChangeSoldOutState(List<Long> spuIds) {
        if (CollectionUtils.isEmpty(spuIds)) {
            return new ResponseWrapper<>(Boolean.TRUE);
        }

        final long currentUserId = 1L;
        spuIds.forEach(
                spuId -> commandGateway.sendAndWait(new ChangeSpuSoldOutCommand(new SpuId(spuId), currentUserId))
        );
        return new ResponseWrapper<>();
    }

    @Override
    @ApiOperation(value = "批量变更退货状态", notes = "批量变更退货状态")
    public ResponseWrapper<Boolean> batchChangeCanReturnState(List<Long> spuIds) {
        if (CollectionUtils.isEmpty(spuIds)) {
            return new ResponseWrapper<>(Boolean.TRUE);
        }

        final long currentUserId = 1L;
        spuIds.forEach(
                spuId -> commandGateway.sendAndWait(new ChangeSpuCanReturnCommand(new SpuId(spuId), currentUserId))
        );
        return new ResponseWrapper<>();
    }
}
