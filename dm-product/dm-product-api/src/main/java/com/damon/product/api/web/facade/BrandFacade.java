package com.damon.product.api.web.facade;

import com.damon.product.api.dto.req.brand.CreateBrandReqDTO;
import com.damon.product.api.dto.req.brand.QueryBrandReqDTO;
import com.damon.product.api.dto.req.brand.UpdateBrandReqDTO;
import com.damon.product.api.dto.resp.brand.BrandInfoRespDTO;
import com.damon.product.shared.constant.ApiConstants;
import com.damon.shared.common.Pagination;
import com.damon.shared.wrapper.ResponseWrapper;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 品牌管理接口
 * @author Damon S.
 */
@RequestMapping(value = ApiConstants.SERVE_NAME + ApiConstants.API_V1, produces = MediaType.APPLICATION_JSON_VALUE)
public interface BrandFacade {


    @PostMapping("/brands")
    ResponseWrapper<Long> create(
            @RequestBody @Validated CreateBrandReqDTO createBrandReqDTO
    );


    @PostMapping("/brands/query")
    ResponseWrapper<Pagination<BrandInfoRespDTO>> query(
            @RequestBody @Validated QueryBrandReqDTO queryBrandReqDTO
    );


    @PutMapping("/brands")
    ResponseWrapper<Boolean> update(
            @RequestBody @Validated UpdateBrandReqDTO updateBrandReqDTO
    );


    @GetMapping("/brands/{brandId}")
    ResponseWrapper<BrandInfoRespDTO> find(
            @PathVariable(name = "brandId") Long brandId
    );


    @DeleteMapping("/brands/{brandId}")
    ResponseWrapper<Boolean> remove(
            @PathVariable(name = "brandId") Long brandId
    );


    @PutMapping("/brands/{brandId}/recovery")
    ResponseWrapper<Boolean> recover(
            @PathVariable(name = "brandId") Long brandId
    );


    @PutMapping("/brands/{brandId}/change_display_state")
    ResponseWrapper<Boolean> changeDisplayState(
            @PathVariable(name = "brandId") Long brandId
    );


    @PutMapping("/brands/{brandId}/change_factory_state")
    ResponseWrapper<Boolean> changeFactoryState(
            @PathVariable(name = "brandId") Long brandId
    );
}
