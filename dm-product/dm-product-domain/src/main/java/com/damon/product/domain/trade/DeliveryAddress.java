package com.damon.product.domain.trade;

import com.damon.product.domain.trade.aggregate.TradeId;
import com.damon.shared.model.ValueObject;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * 收货信息
 * @author Damon S.
 */
@ToString
@Getter
@Builder
public class DeliveryAddress implements ValueObject<DeliveryAddress> {

    private final TradeId tradeId;
    /***收货人*/
    private final String consignee;
    /***地址*/
    private final String address;
    /***电话*/
    private final String phoneNumber;

    @Override
    public boolean sameAs(DeliveryAddress o) {
        return this.getConsignee().equals(o.getConsignee()) &&
                this.getAddress().equals(o.getAddress()) &&
                this.getPhoneNumber().equals(o.getPhoneNumber()) &&
                this.getTradeId().equals(o.getTradeId());
    }
}
