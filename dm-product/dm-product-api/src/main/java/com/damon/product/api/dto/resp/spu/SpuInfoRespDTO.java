package com.damon.product.api.dto.resp.spu;

import com.damon.product.api.dto.resp.sku.SkuInfoRespDTO;
import com.damon.product.shared.enums.ProductType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * SPU返回信息
 * @author Damon S.
 * @version v1.0.1
 * @date 2019年02月24日 23:50
 */
@Data
@ApiModel(value = "SPU对外返回信息")
public class SpuInfoRespDTO implements Serializable {
    private static final Long serialVersionUID = 1L;

    @ApiModelProperty(value = "商品ID")
    private Long        spuId;

    @ApiModelProperty(value = "商品名称")
    private String      name;

    @ApiModelProperty(value = "商品副标题")
    private String      subTitle;

    @ApiModelProperty(value = "商品编码")
    private String      spuCode;

    @ApiModelProperty(value = "主图")
    private Long        imageId;

    @ApiModelProperty(value = "商品图片")
    private List<Long> albumImages;

    @ApiModelProperty(value = "售价（单位：分）")
    private Long        price;

    @ApiModelProperty(value = "市场价（单位：分）")
    private Long        marketPrice;

    @ApiModelProperty(value = "库存")
    private Integer     inventory;

    @ApiModelProperty(value = "型号")
    private String      model;

    @ApiModelProperty(value = "商品类型")
    private ProductType type;

    @ApiModelProperty(value = "是否支持退货(1是，0否)")
    private Integer     supportReturn;

    @ApiModelProperty(value = "是否售罄(1是，0否)")
    private Integer     soldOut;

    @ApiModelProperty(value = "是否新品(1是，0否)")
    private Integer     newProduct;

    @ApiModelProperty(value = "是否推荐(1是，0否)")
    private Integer     recommended;

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
    private List<SkuInfoRespDTO> skus;

    @ApiModelProperty(value = "商品重量（单位：毫克）")
    private Long        weight;

    @ApiModelProperty(value = "商品详情")
    private String      h5Detail;

    @ApiModelProperty(value = "商品说明")
    private String      description;
}
