package com.damon.product.core.web.facade.impl;

import com.damon.product.api.dto.req.spu.CreateProductReqDTO;
import com.damon.product.api.dto.req.spu.SubmitOrderReqDTO;
import com.damon.product.api.dto.resp.spu.CreateProductRespDTO;
import com.damon.product.api.dto.resp.trade.SubmitOrderRespDTO;
import com.damon.product.api.web.facade.ProductFacade;
import com.damon.product.domain.spu.aggregate.SpuId;
import com.damon.product.domain.spu.command.CreateProductCommand;
import com.damon.product.shared.enums.ProductState;
import com.damon.product.shared.enums.ReviewState;
import com.damon.shared.enums.YesNoEnum;
import com.damon.shared.validation.ArgsValid;
import com.damon.shared.wrapper.ResponseWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseWrapper<CreateProductRespDTO> create(
            CreateProductReqDTO createProductReqDTO) {
        Long currentUserId = 10000L;

        CreateProductCommand command = CreateProductCommand.builder()
                .spuId(new SpuId())
                .spuCode(createProductReqDTO.getSpuCode())
                .name(createProductReqDTO.getName())
                .image(createProductReqDTO.getImage())
                .reviewState(ReviewState.AUDITING)
                .productState(ProductState.IN_DRAFT)
                .removed(YesNoEnum.NO)
                .desc(createProductReqDTO.getDesc())
                .price(createProductReqDTO.getPrice())
                .inventory(createProductReqDTO.getInventory())
                .model(createProductReqDTO.getModel())
                .type(createProductReqDTO.getType())
                .canReturn(createProductReqDTO.getCanReturn())
                .categoryId(createProductReqDTO.getCategoryId())
                .brandId(createProductReqDTO.getBrandId())
                .warehouseId(createProductReqDTO.getWarehouseId())
                .supplierId(createProductReqDTO.getSupplierId())
                .h5Detail(createProductReqDTO.getH5Detail())
                .deliverRegion(createProductReqDTO.getDeliveryRegion())
//                .length(createProductReqDTO.getLength())
//                .width(createProductReqDTO.getWidth())
//                .height(createProductReqDTO.getHeight())
//                .weight(createProductReqDTO.getWeight())
//                .boxNum(createProductReqDTO.getBoxNum())
                .createdBy(currentUserId)
                .build();

        commandGateway.sendAndWait(command);
        CreateProductRespDTO createProductRespDTO = new CreateProductRespDTO();

        return new ResponseWrapper<>(createProductRespDTO);
    }

    @Override
    public ResponseWrapper<SubmitOrderRespDTO> submit(
            SubmitOrderReqDTO submitOrderReqDTO) {

        queryGateway.query(null, String.class);
        return null;
    }
}
