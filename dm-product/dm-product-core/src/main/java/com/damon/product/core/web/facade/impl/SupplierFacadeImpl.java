package com.damon.product.core.web.facade.impl;

import com.damon.product.api.dto.req.supplier.CreateSupplierReqDTO;
import com.damon.product.api.dto.req.supplier.QuerySupplierReqDTO;
import com.damon.product.api.dto.req.supplier.UpdateSupplierReqDTO;
import com.damon.product.api.dto.resp.supplier.SupplierInfoRespDTO;
import com.damon.product.api.web.facade.SupplierFacade;
import com.damon.shared.validation.ArgsValid;
import com.damon.shared.wrapper.ResponseWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 供应商管理接口
 * @author Damon S.
 * @version v1.0.1
 * @date 2019年02月23日 21:37
 */
@Api(tags = "供应商管理")
@RestController
@RequiredArgsConstructor
public class SupplierFacadeImpl implements SupplierFacade {

    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;


    @ArgsValid
    @Override
    @ApiOperation(value = "创建品牌", notes = "创建品牌")
    public ResponseWrapper<Boolean> create(CreateSupplierReqDTO createSupplierReqDTO) {
        return null;
    }

    @Override
    public ResponseWrapper<List<SupplierInfoRespDTO>> query(QuerySupplierReqDTO querySupplierReqDTO) {
        return null;
    }


    @ArgsValid
    @Override
    @ApiOperation(value = "创建品牌", notes = "创建品牌")
    public ResponseWrapper<Boolean> update(UpdateSupplierReqDTO updateSupplierReqDTO) {
        return null;
    }


    @Override
    @ApiOperation(value = "创建品牌", notes = "创建品牌")
    public ResponseWrapper<SupplierInfoRespDTO> find(Long supplierId) {
        return null;
    }


    @Override
    @ApiOperation(value = "创建品牌", notes = "创建品牌")
    public ResponseWrapper<Boolean> remove(Long supplierId) {
        return null;
    }
}
