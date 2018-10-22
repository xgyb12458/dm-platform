package com.damon.order.domain.trade;

import com.damon.order.domain.trade.aggregate.TradeId;
import com.damon.shared.model.ValueObject;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * 收货信息
 * @author Damon S.
 */
@Getter
@Builder
@ToString
public class DeliveryAddress implements ValueObject<DeliveryAddress> {

    private TradeId tradeId;
    /***收货人*/
    private String consignee;
    /***地址*/
    private String address;
    /***电话*/
    private String phoneNumber;

    @Override
    public boolean sameAs(DeliveryAddress o) {
        return this.getConsignee().equals(o.getConsignee()) &&
                this.getAddress().equals(o.getAddress()) &&
                this.getPhoneNumber().equals(o.getPhoneNumber()) &&
                this.getTradeId().equals(o.getTradeId());
    }
}
