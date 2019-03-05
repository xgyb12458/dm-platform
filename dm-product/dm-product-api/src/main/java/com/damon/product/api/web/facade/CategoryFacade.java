package com.damon.product.api.web.facade;

import com.damon.product.api.dto.req.category.CreateCategoryReqDTO;
import com.damon.product.api.dto.req.category.QueryCategoryReqDTO;
import com.damon.product.api.dto.req.category.UpdateCategoryReqDTO;
import com.damon.product.api.dto.resp.category.CategoryInfoRespDTO;
import com.damon.product.shared.constant.ApiConstants;
import com.damon.shared.common.Pagination;
import com.damon.shared.wrapper.ResponseWrapper;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 商品类别管理接口
 * @author Damon S.
 */
@RequestMapping(value = ApiConstants.SERVE_NAME + ApiConstants.API_V1, produces = MediaType.APPLICATION_JSON_VALUE)
public interface CategoryFacade {


    @PostMapping("/categories")
    ResponseWrapper<Long> create(
            @RequestBody @Validated CreateCategoryReqDTO createCategoryReqDTO
    );


    @GetMapping("/categories")
    ResponseWrapper<Pagination<CategoryInfoRespDTO>> query(
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


    @DeleteMapping("/categories/{categoryId}")
    ResponseWrapper<Boolean> remove(
            @PathVariable(name = "categoryId") Long categoryId
    );


    @PutMapping("/categories/{categoryId}/recovery")
    ResponseWrapper<Boolean> recover(
            @PathVariable(name = "categoryId") Long categoryId
    );


    @PutMapping("/categories/{categoryId}/change_nav_state")
    ResponseWrapper<Boolean> changeNavState(
            @PathVariable(name = "categoryId") Long categoryId
    );


    @PutMapping("/categories/{categoryId}/change_show_state")
    ResponseWrapper<Boolean> changeShowState(
            @PathVariable(name = "categoryId") Long categoryId
    );
}
