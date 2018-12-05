package com.damon.order.domain.trade;

import com.damon.oauth.domain.user.aggregate.UserId;
import com.damon.order.domain.trade.aggregate.TradeId;
import com.damon.order.shared.enums.OrderState;
import com.damon.order.shared.enums.OrderType;
import com.damon.order.shared.enums.PayState;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * 交易订单信息
 *
 * 基础信息：包含订单号，订单时间，订单状态等信息；
 * @author Damon S.
 */
@ToString
@Getter
@Builder
public class TradeOrder {
    private TradeId tradeId;
    private OrderState orderState;
    private PayState payState;
    private OrderType orderType;

    private UserId createdBy;
    private UserId updatedBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
