package com.damon.order.domain.trade;

import com.damon.order.shared.enums.PayChannel;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * 支付信息
 * @author Damon S.
 */
@Getter
@Builder
@ToString
public class TradePayment {
    /***支付单号*/
    private String              paymentId;
    /***支付渠道*/
    private PayChannel          payChannel;
    /***应付金额*/
    private Long                shouldPayAmt;
    /***实付金额*/
    private Long                actuallyPaid;
    /***付款时间*/
    private LocalDateTime       paidAt;
}
