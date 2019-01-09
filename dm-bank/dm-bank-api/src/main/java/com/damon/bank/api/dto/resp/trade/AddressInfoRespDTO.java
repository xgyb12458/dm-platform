package com.damon.bank.api.dto.resp.trade;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;


/**
 * 投递地址信息
 * @author Damon S.
 */
@Data
@Builder
@ApiModel(value = "投递地址信息")
public class AddressInfoRespDTO implements Serializable {
    private static final Long serialVersionUID = 221L;
    /**地址编号*/
    private final Long addressId;
    /**收货人*/
    private final String consignee;
    /**电话*/
    private final String phoneNumber;
    /**省*/
    private final String province;
    /**市*/
    private final String city;
    /**县*/
    private final String county;
    /**地址*/
    private final String address;
}
