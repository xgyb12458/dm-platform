package com.damon.product.api.web.facade;

import com.damon.product.api.dto.req.warehouse.CreateWarehouseReqDTO;
import com.damon.product.api.dto.req.warehouse.QueryWarehouseReqDTO;
import com.damon.product.api.dto.req.warehouse.UpdateWarehouseReqDTO;
import com.damon.product.api.dto.resp.warehouse.WarehouseInfoRespDTO;
import com.damon.product.shared.constant.ApiConstants;
import com.damon.shared.wrapper.ResponseWrapper;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 仓库管理接口
 * @author Damon S.
 */
@RequestMapping(value = "/product/" + ApiConstants.API_V1, produces = MediaType.APPLICATION_JSON_VALUE)
public interface WarehouseFacade {


    @PostMapping("/warehouses")
    ResponseWrapper<Boolean> create(
            @RequestBody @Validated CreateWarehouseReqDTO createWarehouseReqDTO
    );


    @GetMapping("/warehouses")
    ResponseWrapper<List<WarehouseInfoRespDTO>> query(
            @RequestBody @Validated QueryWarehouseReqDTO queryWarehouseReqDTO
    );


    @PutMapping("/warehouses")
    ResponseWrapper<Boolean> update(
            @RequestBody @Validated UpdateWarehouseReqDTO updateWarehouseReqDTO
    );


    @GetMapping("/warehouses/{warehouseId}")
    ResponseWrapper<WarehouseInfoRespDTO> find(
            @PathVariable(name = "warehouseId") Long warehouseId
    );


//    @PutMapping("/warehouses/{warehouseId}/change_display_state")
//    ResponseWrapper<Boolean> changeDisplayState(
//            @PathVariable(name = "warehouseId") Long warehouseId
//    );
//
//
//    @PutMapping("/warehouses/{warehouseId}/change_factory_state")
//    ResponseWrapper<Boolean> changeFactoryState(
//            @PathVariable(name = "warehouseId") Long warehouseId
//    );


    @DeleteMapping("/warehouses/{warehouseId}")
    ResponseWrapper<Boolean> remove(
            @PathVariable(name = "warehouseId") Long warehouseId
    );
}
