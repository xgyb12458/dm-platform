package com.damon.product.api.dto.req.sku;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 创建SKU请求参数
 * @author Damon S.
 * @version v1.0.1
 * @date 2019年02月26日 23:26
 */
@Data
@ApiModel(value = "创建SKU请求参数")
public class CreateSkuReqDTO implements Serializable {
    private static final Long serialVersionUID = 1L;

    @ApiModelProperty(name = "skuId", value = "skuId")
    private Long      skuId;

    @ApiModelProperty(name = "name", value = "名称")
    private String      name;

    @ApiModelProperty(name = "skuCode", value = "SKU编码")
    private String      skuCode;

    @ApiModelProperty(name = "specIds", value = "规格", notes = "规格Id按顺序排列")
    private List<Long> specIds;

    @ApiModelProperty(name = "imageIds", value = "SKU图片")
    private List<Long>      imageIds;

    @ApiModelProperty(name = "inventory", value = "库存")
    private Integer     inventory;

    @ApiModelProperty(name = "safetyStock", value = "安全库存")
    private Integer     safetyStock;

    @ApiModelProperty(name = "price", value = "价格")
    private Long        price;

    @ApiModelProperty(name = "reduction", value = "立减")
    private Long        reduction;

    @ApiModelProperty(name = "promoteFee", value = "推广费")
    private Long        promoteFee;

    @ApiModelProperty(name = "serviceFee", value = "服务费")
    private Long        serviceFee;

    @ApiModelProperty(name = "exchangePrice", value = "兑换价")
    private Long        exchangePrice;

    @ApiModelProperty(name = "exchangePoint", value = "兑换德分")
    private Long        exchangePoint;

    @ApiModelProperty(name = "netWorth", value = "净值")
    private Long        netWorth;

    @ApiModelProperty(name = "barCode", value = "条形码")
    private String      barCode;
}
