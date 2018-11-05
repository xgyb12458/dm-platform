package com.damon.order.core.web.facade.impl;

import com.damon.order.api.dto.req.trade.ConfirmOrderReqDTO;
import com.damon.order.api.dto.req.trade.SubmitOrderReqDTO;
import com.damon.order.api.dto.resp.trade.ConfirmOrderRespDTO;
import com.damon.order.api.dto.resp.trade.SubmitOrderRespDTO;
import com.damon.order.api.web.facade.OrderFacade;
import com.damon.shared.validation.ArgsValid;
import com.damon.shared.wrapper.ResponseWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RestController;

/**
 * 订单管理接口
 * @author Damon S.
 */
@Api(tags = "订单管理接口")
@RestController
public class OrderFacadeImpl implements OrderFacade {

    @ArgsValid
    @ApiOperation(value = "确认订单", notes = "获取符合查询条件的应用列表")
    @Override
    public ResponseWrapper<ConfirmOrderRespDTO> confirm(
            ConfirmOrderReqDTO confirmOrderReqDTO) {
        return new ResponseWrapper<>();
    }

    @ArgsValid
    @ApiOperation(value = "提交订单", notes = "获取符合查询条件的应用列表")
    @Override
    public ResponseWrapper<SubmitOrderRespDTO> submit(
            SubmitOrderReqDTO submitOrderReqDTO) {
        return new ResponseWrapper<>();
    }
}
