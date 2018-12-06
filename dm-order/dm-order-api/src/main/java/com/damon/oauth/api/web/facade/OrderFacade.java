package com.damon.oauth.api.web.facade;

import com.damon.oauth.api.dto.req.trade.ConfirmOrderReqDTO;
import com.damon.oauth.api.dto.req.trade.SubmitOrderReqDTO;
import com.damon.oauth.api.dto.resp.trade.ConfirmOrderRespDTO;
import com.damon.oauth.api.dto.resp.trade.SubmitOrderRespDTO;
import com.damon.order.shared.constant.ApiConstants;
import com.damon.shared.wrapper.ResponseWrapper;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 订单管理接口
 * @author Damon S.
 */
@RequestMapping(value = "/trade/" + ApiConstants.API_V1, produces = MediaType.APPLICATION_JSON_VALUE)
public interface OrderFacade {


    @PostMapping("/orders/confirm")
    ResponseWrapper<ConfirmOrderRespDTO> confirm(
            @RequestBody @Validated ConfirmOrderReqDTO confirmOrderReqDTO
    );


    @PostMapping("/orders/submit")
    ResponseWrapper<SubmitOrderRespDTO> submit(
            @RequestBody @Validated SubmitOrderReqDTO submitOrderReqDTO
    );
}
