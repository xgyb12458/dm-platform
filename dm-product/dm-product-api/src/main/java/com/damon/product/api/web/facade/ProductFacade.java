package com.damon.product.api.web.facade;

import com.damon.product.api.dto.req.spu.CreateProductReqDTO;
import com.damon.product.api.dto.req.spu.SubmitOrderReqDTO;
import com.damon.product.api.dto.resp.spu.CreateProductRespDTO;
import com.damon.product.api.dto.resp.trade.SubmitOrderRespDTO;
import com.damon.product.shared.constant.ApiConstants;
import com.damon.shared.wrapper.ResponseWrapper;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 商品管理接口
 * @author Damon S.
 */
@RequestMapping(value = "/product/" + ApiConstants.API_V1, produces = MediaType.APPLICATION_JSON_VALUE)
public interface ProductFacade {


    @PostMapping("/spus")
    ResponseWrapper<CreateProductRespDTO> create(
            @RequestBody @Validated CreateProductReqDTO createProductReqDTO
    );


    @PostMapping("/orders/submit")
    ResponseWrapper<SubmitOrderRespDTO> submit(
            @RequestBody @Validated SubmitOrderReqDTO submitOrderReqDTO
    );
}
