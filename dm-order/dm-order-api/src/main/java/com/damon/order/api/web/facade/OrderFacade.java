package com.damon.order.api.web.facade;

import com.damon.order.api.dto.req.trade.ConfirmOrderReqDTO;
import com.damon.order.api.dto.req.trade.QueryOrdersReqDTO;
import com.damon.order.api.dto.req.trade.SubmitOrderReqDTO;
import com.damon.order.api.dto.req.trade.UpdateOrderReqDTO;
import com.damon.order.api.dto.resp.trade.ConfirmOrderRespDTO;
import com.damon.order.api.dto.resp.trade.OrderInfoRespDTO;
import com.damon.order.api.dto.resp.trade.SubmitOrderRespDTO;
import com.damon.order.shared.constant.ApiConstants;
import com.damon.shared.wrapper.ResponseWrapper;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 订单管理接口
 * @author Damon S.
 */
@RequestMapping(value = "/trade/" + ApiConstants.API_V1, produces = MediaType.APPLICATION_JSON_VALUE)
public interface OrderFacade {

    @GetMapping("/orders")
    ResponseWrapper<OrderInfoRespDTO> list(
            @RequestBody @Validated QueryOrdersReqDTO queryOrdersReqDTO
    );

    @PutMapping("/orders")
    ResponseWrapper<Boolean> update(
            @RequestBody @Validated UpdateOrderReqDTO updateOrderReqDTO
    );

    @GetMapping("/orders/{orderId}")
    ResponseWrapper<OrderInfoRespDTO> find(
            @PathVariable("orderId") Long orderId
    );

    @PutMapping("/orders/{orderId}/cancel")
    ResponseWrapper<OrderInfoRespDTO> cancel(
            @PathVariable("orderId") Long orderId
    );

    @PutMapping("/orders/{orderId}/pay")
    ResponseWrapper<OrderInfoRespDTO> pay(
            @PathVariable("orderId") Long orderId
    );

    @DeleteMapping("/orders/{orderId}")
    ResponseWrapper<OrderInfoRespDTO> remove(
            @PathVariable("orderId") Long orderId
    );

    @GetMapping("/orders/confirm")
    ResponseWrapper<ConfirmOrderRespDTO> confirm(
            @RequestBody @Validated ConfirmOrderReqDTO confirmOrderReqDTO
    );

    @PostMapping("/orders/submit")
    ResponseWrapper<SubmitOrderRespDTO> submit(
            @RequestBody @Validated SubmitOrderReqDTO submitOrderReqDTO
    );
}
