package com.damon.order.domain.trade.event;

import com.damon.order.domain.trade.aggregate.TradeId;
import com.damon.order.shared.enums.PayChannel;
import lombok.Builder;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.List;


/***
 * 交易创建完成事件
 * @author Damon S.
 */
@Getter
@Builder
public class TradeCreatedEvent {
    @TargetAggregateIdentifier
    private final TradeId tradeId;
    private final AddressId addressId;
    private final List<OrderSku> skus;
    private final String message;
    private final InvoiceId invoiceId;
    private final Long integration;
    private final Long commission;
    private final List<CouponId> couponIds;
    private final PayChannel payChannel;
    private final UserId createdBy;
}
