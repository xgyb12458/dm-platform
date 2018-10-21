package com.damon.order.domain.trade;

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
public class ReceiptAddress implements ValueObject<ReceiptAddress> {

    /***收货人*/
    private String consignee;
    /***省*/
    private String province;
    /***市*/
    private String city;
    /***区县*/
    private String county;
    /***地址*/
    private String address;
    /***电话*/
    private String phoneNumber;

    @Override
    public boolean sameAs(ReceiptAddress o) {
        return this.getConsignee().equals(o.getConsignee()) &&
                this.getAddress().equals(o.getAddress()) &&
                this.getPhoneNumber().equals(o.getPhoneNumber());
    }
}
