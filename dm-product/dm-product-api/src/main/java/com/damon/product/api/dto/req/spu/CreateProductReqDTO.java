package com.damon.product.api.dto.req.spu;

import com.damon.product.shared.enums.ProductModel;
import com.damon.product.shared.enums.ProductType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

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

    @ApiModelProperty(name = "soldVolume", value = "销量")
    private Integer     soldVolume;

    @ApiModelProperty(name = "deliverRegion", value = "配送区域")
    private String      deliveryRegion;

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
}
