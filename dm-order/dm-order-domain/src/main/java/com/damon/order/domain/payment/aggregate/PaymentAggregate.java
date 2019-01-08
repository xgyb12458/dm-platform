package com.damon.order.domain.payment.aggregate;

import com.damon.order.domain.payment.command.PayOrderCommand;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

/**
 * 订单支付
 * @author Damon S.
 */
@Slf4j
@Getter
@Setter(value = AccessLevel.PRIVATE)
@ToString
@Aggregate
@NoArgsConstructor
public class PaymentAggregate {

    @AggregateIdentifier
    private PaymentId paymentId;

    @CommandHandler
    public PaymentAggregate(PayOrderCommand command) {

    }
}
