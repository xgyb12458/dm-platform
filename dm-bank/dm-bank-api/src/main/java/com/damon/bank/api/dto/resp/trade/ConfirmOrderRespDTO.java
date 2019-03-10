package com.damon.bank.api.dto.resp.trade;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.List;

/**
 * 确认订单返回数据
 * @author Damon S.
 */
@Data
@Builder
@ApiModel(value = "确认订单返回数据")
public class ConfirmOrderRespDTO implements Serializable {
    private static final Long serialVersionUID = 21L;

    @ApiModelProperty(value = "投递地址")
    private ConfirmAddressRespDTO address;

    @ApiModelProperty(value = "订单商品SKU", required = true)
    private List<ConfirmSkuRespDTO> skus;

    @ApiModelProperty(value = "可抵扣德分")
    @Min(0L)
    private Long integration;

    @ApiModelProperty(value = "可抵扣收益")
    @Min(0L)
    private Long commission;

    @ApiModelProperty(value = "可用优惠券")
    private List<ConfirmCouponRespDTO> coupons;


    /**
     * 确认订单配送地址信息
     * @author Damon S.
     */
    @Data
    @ToString
    @ApiModel(value = "配送地址信息")
    public static class ConfirmAddressRespDTO implements Serializable {
        private static final Long serialVersionUID = 1L;

        @ApiModelProperty(value = "地址Id")
        private Long addressId;

        @ApiModelProperty(value = "收货人")
        private String consignee;

        @ApiModelProperty(value = "联系电话")
        private String phone;

        @ApiModelProperty(value = "详细地址")
        private String address;

        @ApiModelProperty(value = "配送费")
        private Long logisticsFee;
    }


    /**
     * 确认订单可用优惠券信息
     * @author Damon S.
     */
    @Data
    @ToString
    @ApiModel(value = "可用优惠券")
    public static class ConfirmCouponRespDTO implements Serializable {
        private static final Long serialVersionUID = 1L;

        @ApiModelProperty(value = "优惠券Id")
        private Long couponId;

        @ApiModelProperty(value = "优惠券金额")
        private Long amount;
    }


    /**
     * 确认订单商品SKU
     * @author Damon S.
     */
    @Data
    @ToString
    @ApiModel(value = "订单商品SKU")
    public static class ConfirmSkuRespDTO implements Serializable {
        private static final Long serialVersionUID = 1L;

        @ApiModelProperty(value = "购物车项Id")
        private Long cid;

        @ApiModelProperty(value = "商品SkuId")
        private Long skuid;

        @ApiModelProperty(value = "购买数量")
        private Integer qty;

        @ApiModelProperty(value = "活动编号")
        private Long pid;
    }
}
