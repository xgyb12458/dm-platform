package com.damon.order.domain.trade.aggregate;

import com.damon.order.domain.trade.*;
import com.damon.order.domain.trade.command.SubmitOrderCommand;
import com.damon.order.domain.trade.event.TradeCreatedEvent;
import lombok.*;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

/**
 * 一次交易(主订单)
 * @author Damon S.
 */
@Getter
@Setter(value = AccessLevel.PRIVATE)
@ToString
@Aggregate
@NoArgsConstructor
public class TradeAggregate {
    /**
     * 基础信息：包含订单号，订单时间，订单状态等信息；
     * 用户信息：涉及用户的信息，比如买家姓名、注册手机号、收件人等信息；
     * 商品信息：涉及订单中的商品字段，主要包括商品名称、单价、数量、所属店铺等；
     * 支付信息：涉及支付的字段信息，主要包括支付方式、支付状态、支付时间、支付单号、支付金额、订单金额、优惠金额等；
     * 时间信息：涉及订单流转中各个时间戳的字段，包括下单时间、支付时间、发货时间、完成时间等
     * 权益资产：包含红包，卡券，积分，京豆等虚拟资产；
     * 促销信息：涉及促销的字段信息，主要包括优惠方式、优惠面额、折扣等；
     * 状态信息：涉及订单流转中状态变更的字段，主要包括订单状态、物流状态及退款状态等；
     * 发票信息：发票类型选择，增值税普通发票还是增值税专用发票，电子发票还是不开票等信息；
     * 物流信息：物流具体的状态，什么时间点，哪个站点/仓，哪位配送员进行配送，是否签收，节点时间等信息。
     * 配送信息：涉及订单配送的基本信息，比如配送方式、物流单号等；
     *
     * 商品信息需要从商品获取，促销信息从促销系统获取，库存从库存系统获取，
     * 支付信息从支付系统获取，发票从发票系统，业务表示从对应的业务系统获取.
     *
     * 生成订单后，还要进行订单拆分，包含优惠拆分和订单拆分，紧接着进入wms系统，最后走财务开票了流程。
     */

    private static final Logger LOGGER = LoggerFactory.getLogger(TradeAggregate.class);

    @AggregateIdentifier
    private TradeId             tradeId;
    /***交易主订单信息*/
    private TradeOrder          tradeOrder;
    /***交易商品信息*/
    private TradeProduct        product;
    /***投递信息*/
    private DeliveryAddress     address;
    /***支付信息*/
    private TradePayment        payment;
    /***优惠折扣信息*/
    private TradeDiscount       discount;


    @CommandHandler
    public TradeAggregate(SubmitOrderCommand command) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Order submit command ");
        }

        apply(TradeCreatedEvent.builder()
                .tradeId(command.getTradeId())
                .addressId(command.getAddressId())
                .skus(command.getSkus())
                .commission(command.getCommission())
                .couponIds(command.getCouponIds())
                .invoiceId(command.getInvoiceId())
                .message(command.getMessage())
                .integration(command.getIntegration())
                .payChannel(command.getPayChannel())
                .createdBy(command.getCreatedBy())
                .build()
        );
    }

    @EventSourcingHandler
    public void on(TradeCreatedEvent event) {
        setTradeId(event.getTradeId());
        setTradeOrder(TradeOrder.builder()
                .createdBy(event.getCreatedBy())
                .build()
        );
        setAddress(DeliveryAddress.builder()
                .consignee("")
                .phoneNumber("")
                .build()
        );
        setPayment(TradePayment.builder()
                .paymentId("")
                .build()
        );
        setDiscount(TradeDiscount.builder()
                .build()
        );
    }
}
