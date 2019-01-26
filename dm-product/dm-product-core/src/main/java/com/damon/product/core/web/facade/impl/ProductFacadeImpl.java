package com.damon.product.core.web.facade.impl;

import com.damon.product.api.dto.req.spu.CreateProductReqDTO;
import com.damon.product.api.dto.req.spu.SubmitOrderReqDTO;
import com.damon.product.api.dto.resp.spu.CreateProductRespDTO;
import com.damon.product.api.dto.resp.trade.SubmitOrderRespDTO;
import com.damon.product.api.web.facade.ProductFacade;
import com.damon.product.domain.spu.aggregate.SpuAdapter;
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
        commandGateway.sendAndWait(
                SpuAdapter.transformCommand(createProductReqDTO)
        );
        CreateProductRespDTO createProductRespDTO = new CreateProductRespDTO();

        return new ResponseWrapper<>(createProductRespDTO);
    }

    @ArgsValid
    @Override
    @ApiOperation(value = "创建商品", notes = "创建商品参数")
    public ResponseWrapper<SubmitOrderRespDTO> submit(
            SubmitOrderReqDTO submitOrderReqDTO) {

        queryGateway.query(null, String.class);
        return null;
    }
}
