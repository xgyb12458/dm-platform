package com.damon.order.api.dto.req.trade;

import com.damon.shared.dto.SecurityReqDTO;
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
 * 确认订单请求
 * @author Damon S.
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "确认订单参数")
public final class ConfirmOrderReqDTO extends SecurityReqDTO implements Serializable {
    private static final Long serialVersionUID = 1L;

    @NotNull(message = "skus不能为空")
    @ApiModelProperty(name = "skus", value = "确认订单商品SKU", required = true)
    private List<ConfirmSkuReqDTO> skus;


    /**
     * 确认订单商品SKU
     * @author Damon S.
     */
    @Data
    @ToString
    @ApiModel(value = "确认订单商品SKU")
    public static class ConfirmSkuReqDTO implements Serializable {
        private static final Long serialVersionUID = 1L;

        @NotNull(message = "sku不能为空")
        @ApiModelProperty(name = "skuId", value = "skuId", required = true)
        private String sku;

        @Pattern(regexp = "^[1-9]\\d*$", message = "数量必须大于零")
        @ApiModelProperty(name = "quantity", value = "购买数量", required = true)
        @Min(1)
        private Integer qty;

        @ApiModelProperty(name = "promotionId", value = "活动编号")
        private String pId;

        @ApiModelProperty(name = "detailId", value = "活动详情编号")
        private String dId;
    }
}
