package com.damon.order.api.dto.req.trade;

import com.damon.order.shared.enums.PayChannel;
import com.damon.shared.common.Constants;
import com.damon.shared.dto.SecurityReqDTO;
import com.damon.shared.enums.InvoiceType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.List;

/**
 * 提交订单命令
 * @author Damon S.
 */
@Data
//@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "提交订单参数")
public class SubmitOrderReqDTO extends SecurityReqDTO implements Serializable {
    private static final Long serialVersionUID = 1L;

    @NotNull(message = "配送地址不能为空")
    @ApiModelProperty(name = "addressId", value = "配送地址", required = true)
    @Min(0L)
    private Long addressId;

    @NotNull(message = "订单商品SKU不能为空")
    @ApiModelProperty(name = "skus", value = "订单商品SKU", required = true)
    private List<SubmitSkuReqDTO> skus;

    @ApiModelProperty(name = "message", value = "买家留言")
    private String message;

    @ApiModelProperty(name = "invoiceType", value = "发票类型")
    private InvoiceType invoiceType;

    @ApiModelProperty(name = "invoiceId", value = "发票信息")
    @Min(0L)
    private Long invoiceId;

    @ApiModelProperty(name = "point", value = "德分抵扣")
    @Min(0L)
    private Long point;

    @ApiModelProperty(name = "commission", value = "收益抵扣")
    @Min(0L)
    private Long commission;

    @ApiModelProperty(name = "couponIds", value = "使用优惠券")
    private List<String> couponIds;

    @NotNull(message = "支付渠道不能为空")
    @ApiModelProperty(name = "payChannel", value = "支付渠道", required = true)
    private PayChannel payChannel;


    /**
     * 提交订单商品SKU
     * @author Damon S.
     */
    @Data
    @ToString
    @ApiModel(value = "提交订单商品SKU")
    public static class SubmitSkuReqDTO implements Serializable {
        private static final Long serialVersionUID = 1L;

        @NotNull(message = "sku不能为空")
        @ApiModelProperty(name = "skuId", value = "skuId", required = true)
        private String sku;

        @Pattern(regexp = "^[1-9]\\d*$", message = "数量必须大于零")
        @ApiModelProperty(name = "quantity", value = "购买数量", required = true)
        private Integer qty;

        @ApiModelProperty(name = "promotionId", value = "活动编号")
        private String pId;

        @ApiModelProperty(name = "detailId", value = "活动详情编号")
        private String dId;
    }

    @Override
    public String toString() {
        String signBody = Constants.EMPTY;
        return signBody.concat("addressId=" + getAddressId())
                .concat("&skus=" + getSkus())
                .concat("&message=" + getMessage())
                .concat("&invoiceType=" + getInvoiceType())
                .concat("&invoiceId=" + getInvoiceId())
                .concat("&point=" + getPoint())
                .concat("&commission=" + getCommission())
                .concat("&couponIds=" + getCouponIds())
                .concat("&payChannel=" + getPayChannel());
    }
}

