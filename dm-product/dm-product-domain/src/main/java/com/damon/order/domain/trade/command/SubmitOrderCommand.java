package com.damon.order.domain.trade.command;

import com.damon.oauth.domain.user.aggregate.*;
import com.damon.order.domain.trade.aggregate.TradeId;
import com.damon.order.shared.enums.PayChannel;
import lombok.Builder;
import lombok.Getter;
import org.axonframework.modelling.command.AggregateIdentifier;

import java.util.List;

/**
 * 提交订单命令
 * @author Damon S.
 */
@Getter
@Builder
public class SubmitOrderCommand {
    @AggregateIdentifier
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
