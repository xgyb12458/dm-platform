package com.damon.product.core.web.facade.impl;

import com.damon.product.api.dto.req.spu.CreateSpuReqDTO;
import com.damon.product.api.dto.resp.spu.CreateSpuRespDTO;
import com.damon.product.api.web.facade.ProductFacade;
import com.damon.product.core.query.handler.spu.SpuAdapter;
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
    public ResponseWrapper<CreateSpuRespDTO> create(
            CreateSpuReqDTO createSpuReqDTO) {
        commandGateway.sendAndWait(
                SpuAdapter.transformCommand(createSpuReqDTO)
        );
        CreateSpuRespDTO createSpuRespDTO = new CreateSpuRespDTO();

        return new ResponseWrapper<>(createSpuRespDTO);
    }

//    @ArgsValid
//    @Override
//    @ApiOperation(value = "创建商品", notes = "创建商品参数")
//    public ResponseWrapper<SubmitOrderRespDTO> submit(
//            SubmitOrderReqDTO submitOrderReqDTO) {
//
//        queryGateway.query(null, String.class);
//        return null;
//    }
}
