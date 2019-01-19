package com.damon.product.api.dto.req.spu;

import com.damon.product.shared.enums.ProductModel;
import com.damon.product.shared.enums.ProductType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 创建商品请求
 * @author Damon S.
 */
@Data
@ApiModel(value = "创建商品参数")
public class CreateProductReqDTO implements Serializable {
    private static final Long serialVersionUID = 12L;

    @ApiModelProperty(name = "name", value = "商品名称")
    private String      name;

    @ApiModelProperty(name = "spuCode", value = "商品编码")
    private String      spuCode;

    @ApiModelProperty(name = "image", value = "商品主图")
    private String      image;

    @ApiModelProperty(name = "desc", value = "商品说明")
    private String      desc;

    @ApiModelProperty(name = "price", value = "商品价格")
    private Long        price;

    @ApiModelProperty(name = "inventory", value = "库存")
    private Integer     inventory;

    @ApiModelProperty(name = "model", value = "型号")
    private ProductModel model;

    @ApiModelProperty(name = "type", value = "商品类型")
    private ProductType type;

    @ApiModelProperty(name = "canReturn", value = "是否支持退货")
    private Boolean     canReturn;

    @ApiModelProperty(name = "categoryId", value = "商品类别")
    private Long        categoryId;

    @ApiModelProperty(name = "brandId", value = "商品品牌")
    private Long        brandId;

    @ApiModelProperty(name = "warehouseId", value = "仓库")
    private Long        warehouseId;

    @ApiModelProperty(name = "supplierId", value = "供应商")
    private Long        supplierId;

    @ApiModelProperty(name = "h5Detail", value = "H5详情")
    private String      h5Detail;

    @ApiModelProperty(name = "deliverRegion", value = "配送区域")
    private String      deliveryRegion;

    @ApiModelProperty(name = "skus", value = "单/多规格")
    private List<ProductSkuReqDTO> skus;

    @ApiModelProperty(name = "length", value = "长")
    private Long        length;

    @ApiModelProperty(name = "width", value = "宽")
    private Long        width;

    @ApiModelProperty(name = "height", value = "高")
    private Long        height;

    @ApiModelProperty(name = "weight", value = "重")
    private Long        weight;

    @ApiModelProperty(name = "boxNum", value = "装箱数")
    private Long        boxNum;


    /**
     * 创建商品请求
     * @author Damon S.
     */
    @Data
    @ApiModel(value = "创建商品SKU参数")
    public class ProductSkuReqDTO implements Serializable {
        @ApiModelProperty(name = "skuCode", value = "Sku编码")
        private String      skuCode;

        @ApiModelProperty(name = "name", value = "名称")
        private String      name;

        @ApiModelProperty(name = "image", value = "SKU图片")
        private String      image;

        @ApiModelProperty(name = "inventory", value = "库存")
        private Integer     inventory;

        @ApiModelProperty(name = "secureInventory", value = "安全库存")
        private Integer     secureInventory;

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
}
