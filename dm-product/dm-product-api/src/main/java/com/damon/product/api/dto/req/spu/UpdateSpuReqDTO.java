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
@ApiModel(value = "更新商品")
public class UpdateSpuReqDTO implements Serializable {
    private static final Long serialVersionUID = 1L;

    @ApiModelProperty(value = "商品Id")
    private Long        spuId;

    @ApiModelProperty(value = "商品名称")
    private String      name;

    @ApiModelProperty(value = "商品编码")
    private String      spuCode;

    @ApiModelProperty(value = "商品副标题")
    private String      subTitle;

    @ApiModelProperty(value = "主图")
    private Long        imageId;

    @ApiModelProperty(value = "商品图片")
    private List<Long>  albumImages;

    @ApiModelProperty(value = "售价（单位：分）")
    private Long        price;

    @ApiModelProperty(value = "市场价（单位：分）")
    private Long        marketPrice;

    @ApiModelProperty(value = "库存")
    private Integer     inventory;

    @ApiModelProperty(value = "安全库存")
    private Integer     safetyStock;

    @ApiModelProperty(value = "销量")
    private Integer     soldVolume;

    @ApiModelProperty(value = "型号")
    private String      model;

    @ApiModelProperty(value = "商品类型")
    private ProductType type;

    @ApiModelProperty(value = "是否支持退货")
    private Boolean     supportReturn;

    @ApiModelProperty(value = "是否售罄")
    private Boolean     soldOut;

    @ApiModelProperty(value = "是否新品")
    private Boolean     newProduct;

    @ApiModelProperty(value = "是否推荐")
    private Boolean     recommended;

    @ApiModelProperty(value = "类别")
    private Long        categoryId;

    @ApiModelProperty(value = "品牌")
    private Long        brandId;

    @ApiModelProperty(value = "仓库")
    private Long        warehouseId;

    @ApiModelProperty(value = "供应商")
    private Long        supplierId;

    @ApiModelProperty(value = "运费模板")
    private Long        freightTemplateId;

    @ApiModelProperty(value = "配送区域")
    private String      deliveryRegion;

    @ApiModelProperty(value = "单/多规格")
    private List<UpdateSkuReqDTO> skus;

    @ApiModelProperty(value = "商品重量（单位：毫克）")
    private Long        weight;

    @ApiModelProperty(value = "商品详情")
    private String      h5Detail;

    @ApiModelProperty(value = "商品说明")
    private String      description;
}
