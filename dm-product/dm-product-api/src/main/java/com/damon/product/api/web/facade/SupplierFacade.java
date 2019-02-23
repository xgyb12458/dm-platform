package com.damon.product.api.web.facade;

import com.damon.product.api.dto.req.supplier.CreateSupplierReqDTO;
import com.damon.product.api.dto.req.supplier.QuerySupplierReqDTO;
import com.damon.product.api.dto.req.supplier.UpdateSupplierReqDTO;
import com.damon.product.api.dto.resp.supplier.SupplierInfoRespDTO;
import com.damon.product.shared.constant.ApiConstants;
import com.damon.shared.wrapper.ResponseWrapper;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 供应商管理接口
 * @author Damon S.
 */
@RequestMapping(value = "/product/" + ApiConstants.API_V1, produces = MediaType.APPLICATION_JSON_VALUE)
public interface SupplierFacade {


    @PostMapping("/suppliers")
    ResponseWrapper<Boolean> create(
            @RequestBody @Validated CreateSupplierReqDTO createSupplierReqDTO
    );


    @GetMapping("/suppliers")
    ResponseWrapper<List<SupplierInfoRespDTO>> query(
            @RequestBody @Validated QuerySupplierReqDTO querySupplierReqDTO
    );


    @PutMapping("/suppliers")
    ResponseWrapper<Boolean> update(
            @RequestBody @Validated UpdateSupplierReqDTO updateSupplierReqDTO
    );


    @GetMapping("/suppliers/{supplierId}")
    ResponseWrapper<SupplierInfoRespDTO> find(
            @PathVariable(name = "supplierId") Long supplierId
    );


//    @PutMapping("/suppliers/{supplierId}/change_display_state")
//    ResponseWrapper<Boolean> changeDisplayState(
//            @PathVariable(name = "supplierId") Long supplierId
//    );
//
//
//    @PutMapping("/suppliers/{supplierId}/change_factory_state")
//    ResponseWrapper<Boolean> changeFactoryState(
//            @PathVariable(name = "supplierId") Long supplierId
//    );


    @DeleteMapping("/suppliers/{supplierId}")
    ResponseWrapper<Boolean> remove(
            @PathVariable(name = "supplierId") Long supplierId
    );
}
