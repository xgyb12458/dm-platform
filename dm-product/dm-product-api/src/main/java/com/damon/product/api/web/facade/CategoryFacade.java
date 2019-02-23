package com.damon.product.api.web.facade;

import com.damon.product.api.dto.resp.category.CategoryInfoRespDTO;
import com.damon.product.api.dto.req.category.QueryCategoryReqDTO;
import com.damon.product.api.dto.req.category.UpdateCategoryReqDTO;
import com.damon.product.api.dto.req.spu.CreateSpuReqDTO;
import com.damon.product.shared.constant.ApiConstants;
import com.damon.shared.wrapper.ResponseWrapper;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品类别管理接口
 * @author Damon S.
 */
@RequestMapping(value = "/product/" + ApiConstants.API_V1, produces = MediaType.APPLICATION_JSON_VALUE)
public interface CategoryFacade {


    @PostMapping("/categories")
    ResponseWrapper<Boolean> create(
            @RequestBody @Validated CreateSpuReqDTO createSpuReqDTO
    );


    @GetMapping("/categories")
    ResponseWrapper<List<CategoryInfoRespDTO>> query(
            @RequestBody @Validated QueryCategoryReqDTO queryCategoryReqDTO
    );


    @PutMapping("/categories")
    ResponseWrapper<Boolean> update(
            @RequestBody @Validated UpdateCategoryReqDTO updateCategoryReqDTO
    );


    @GetMapping("/categories/{categoryId}")
    ResponseWrapper<CategoryInfoRespDTO> find(
            @PathVariable(name = "categoryId") Long categoryId
    );


//    @PutMapping("/categories/{categoryId}/change_display_state")
//    ResponseWrapper<Boolean> changeDisplayState(
//            @PathVariable(name = "categoryId") Long categoryId
//    );
//
//
//    @PutMapping("/categories/{categoryId}/change_factory_state")
//    ResponseWrapper<Boolean> changeFactoryState(
//            @PathVariable(name = "categoryId") Long categoryId
//    );


    @DeleteMapping("/categories/{categoryId}")
    ResponseWrapper<Boolean> remove(
            @PathVariable(name = "categoryId") Long categoryId
    );
}
