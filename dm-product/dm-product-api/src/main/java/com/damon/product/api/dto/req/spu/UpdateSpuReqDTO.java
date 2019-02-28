package com.damon.product.api.dto.req.spu;

import com.damon.product.api.dto.req.sku.UpdateSkuReqDTO;
import com.damon.product.shared.enums.ProductType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 更新商品请求
 * @author Damon S.
 */
@Data
@ApiModel(value = "创建商品请求参数")
public class UpdateSpuReqDTO implements Serializable {
    private static final Long serialVersionUID = 1L;

    @ApiModelProperty(name = "spuId", value = "商品Id")
    private Long        spuId;

    @ApiModelProperty(name = "name", value = "商品名称")
    private String      name;

    @ApiModelProperty(name = "spuCode", value = "商品编码")
    private String      spuCode;

    @ApiModelProperty(name = "subTitle", value = "商品副标题")
    private String      subTitle;

    @ApiModelProperty(name = "imageId", value = "主图")
    private Long        imageId;

    @ApiModelProperty(name = "albumImages", value = "商品图片")
    private List<Long>  albumImages;

    @ApiModelProperty(name = "price", value = "售价（单位：分）")
    private Long        price;

    @ApiModelProperty(name = "marketPrice", value = "市场价（单位：分）")
    private Long        marketPrice;

    @ApiModelProperty(name = "inventory", value = "库存")
    private Integer     inventory;

    @ApiModelProperty(name = "safetyStock", value = "安全库存")
    private Integer     safetyStock;

    @ApiModelProperty(name = "soldVolume", value = "销量")
    private Integer     soldVolume;

    @ApiModelProperty(name = "model", value = "型号")
    private String      model;

    @ApiModelProperty(name = "type", value = "商品类型")
    private ProductType type;

    @ApiModelProperty(name = "supportReturn", value = "是否支持退货")
    private Boolean     supportReturn;

    @ApiModelProperty(name = "soldOut", value = "是否售罄")
    private Boolean     soldOut;

    @ApiModelProperty(name = "newProduct", value = "是否新品")
    private Boolean     newProduct;

    @ApiModelProperty(name = "recommended", value = "是否推荐")
    private Boolean     recommended;

    @ApiModelProperty(name = "categoryId", value = "类别")
    private Long        categoryId;

    @ApiModelProperty(name = "brandId", value = "品牌")
    private Long        brandId;

    @ApiModelProperty(name = "warehouseId", value = "仓库")
    private Long        warehouseId;

    @ApiModelProperty(name = "supplierId", value = "供应商")
    private Long        supplierId;

    @ApiModelProperty(name = "freightTemplateId", value = "运费模板")
    private Long        freightTemplateId;

    @ApiModelProperty(name = "deliverRegion", value = "配送区域")
    private String      deliveryRegion;

    @ApiModelProperty(name = "skus", value = "单/多规格")
    private List<UpdateSkuReqDTO> skus;

    @ApiModelProperty(name = "weight", value = "商品重量（单位：毫克）")
    private Long        weight;

    @ApiModelProperty(name = "h5Detail", value = "商品详情")
    private String      h5Detail;

    @ApiModelProperty(name = "description", value = "商品说明")
    private String      description;
}
