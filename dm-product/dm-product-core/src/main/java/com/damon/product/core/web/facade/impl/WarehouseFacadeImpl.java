package com.damon.product.core.web.facade.impl;

import com.damon.product.api.dto.req.warehouse.CreateWarehouseReqDTO;
import com.damon.product.api.dto.req.warehouse.QueryWarehouseReqDTO;
import com.damon.product.api.dto.req.warehouse.UpdateWarehouseReqDTO;
import com.damon.product.api.dto.resp.warehouse.WarehouseInfoRespDTO;
import com.damon.product.api.web.facade.WarehouseFacade;
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
 * 仓库管理接口
 * @author Damon S.
 * @version v1.0.1
 * @date 2019年02月23日 21:43
 */
@Api(tags = "仓库管理接口")
@RestController
@RequiredArgsConstructor
public class WarehouseFacadeImpl implements WarehouseFacade {

    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;


    @ArgsValid
    @Override
    @ApiOperation(value = "创建品牌", notes = "创建品牌")
    public ResponseWrapper<Boolean> create(CreateWarehouseReqDTO createWarehouseReqDTO) {
        return null;
    }


    @ArgsValid
    @Override
    @ApiOperation(value = "创建品牌", notes = "创建品牌")
    public ResponseWrapper<List<WarehouseInfoRespDTO>> query(QueryWarehouseReqDTO queryWarehouseReqDTO) {
        return null;
    }


    @ArgsValid
    @Override
    @ApiOperation(value = "创建品牌", notes = "创建品牌")
    public ResponseWrapper<Boolean> update(UpdateWarehouseReqDTO updateWarehouseReqDTO) {
        return null;
    }


    @Override
    @ApiOperation(value = "创建品牌", notes = "创建品牌")
    public ResponseWrapper<WarehouseInfoRespDTO> find(Long warehouseId) {
        return null;
    }


    @Override
    @ApiOperation(value = "创建品牌", notes = "创建品牌")
    public ResponseWrapper<Boolean> remove(Long warehouseId) {
        return null;
    }
}
