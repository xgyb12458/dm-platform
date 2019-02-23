package com.damon.product.api.web.facade;

import com.damon.product.api.dto.req.spu.CreateSpuReqDTO;
import com.damon.product.api.dto.resp.spu.CreateSpuRespDTO;
import com.damon.product.shared.constant.ApiConstants;
import com.damon.shared.wrapper.ResponseWrapper;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 仓库管理接口
 * @author Damon S.
 */
@RequestMapping(value = "/product/" + ApiConstants.API_V1, produces = MediaType.APPLICATION_JSON_VALUE)
public interface WarehouseFacade {


    @PostMapping("/warehouse")
    ResponseWrapper<CreateSpuRespDTO> create(
            @RequestBody @Validated CreateSpuReqDTO createSpuReqDTO
    );


//    @PostMapping("/orders/submit")
//    ResponseWrapper<SubmitOrderRespDTO> submit(
//            @RequestBody @Validated SubmitOrderReqDTO submitOrderReqDTO
//    );
}
