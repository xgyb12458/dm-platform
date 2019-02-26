package com.damon.product.api.dto.req.spu;

import com.damon.product.api.dto.req.sku.CreateSkuReqDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 创建商品请求
 * @author Damon S.
 */
@Data
@ApiModel(value = "创建商品请求参数")
public class CreateSpuReqDTO implements Serializable {
    private static final Long serialVersionUID = 1L;

    @ApiModelProperty(name = "name", value = "商品名称")
    @NotBlank(message = "商品名称不能为空")
    private String      name;

    @ApiModelProperty(name = "subTitle", value = "商品副标题")
    @NotBlank(message = "副标题不能为空")
    private String      subTitle;

    @ApiModelProperty(name = "spuCode", value = "商品编码")
    @NotBlank(message = "商品编码不能为空")
    private String      spuCode;

    @ApiModelProperty(name = "imageId", value = "主图")
    @NotNull(message = "主图不能为空")
    private Long        imageId;

    @ApiModelProperty(name = "albumImages", value = "商品图片")
    private List<Long>  albumImages;

    @ApiModelProperty(name = "price", value = "售价（单位：分）")
    @NotNull(message = "售价不能为空")
    private Long        price;

    @ApiModelProperty(name = "marketPrice", value = "市场价（单位：分）")
    private Long        marketPrice;

    @ApiModelProperty(name = "inventory", value = "库存")
    @Min(value = 0, message = "库存不合法")
    private Integer     inventory;

    @ApiModelProperty(name = "safetyStock", value = "安全库存")
    @Min(value = 0, message = "安全库存不合法")
    private Integer     safetyStock;

    @ApiModelProperty(name = "model", value = "型号")
    private String      model;

    @ApiModelProperty(name = "type", value = "商品类型(0普通商品，1套装商品)")
    @Min(value = 0, message = "type取值不合法")
    @Max(value = 1, message = "type取值不合法")
    private Integer     type;

    @ApiModelProperty(name = "supportReturn", value = "是否支持退货(1是，0否)")
    @Min(value = 0, message = "supportReturn取值不合法")
    @Max(value = 1, message = "supportReturn取值不合法")
    private Integer     supportReturn;

    @ApiModelProperty(name = "deliveryRegion", value = "配送区域")
    @NotBlank(message = "配送区域不能为空")
    private String      deliveryRegion;

    @ApiModelProperty(name = "newProduct", value = "是否新品(1是，0否)")
    @Min(value = 0, message = "newProduct取值不合法")
    @Max(value = 1, message = "newProduct取值不合法")
    private Integer     newProduct;

    @ApiModelProperty(name = "recommended", value = "是否推荐(1是，0否)")
    @Min(value = 0, message = "recommended取值不合法")
    @Max(value = 1, message = "recommended取值不合法")
    private Integer     recommended;

    @ApiModelProperty(name = "categoryId", value = "类别")
    private Long        categoryId;

    @ApiModelProperty(name = "brandId", value = "品牌")
    private Long        brandId;

    @ApiModelProperty(name = "warehouseId", value = "仓库")
    private Long        warehouseId;

    @ApiModelProperty(name = "supplierId", value = "供应商")
    private Long        supplierId;

    @ApiModelProperty(name = "freightTemplateId", value = "运费模板")
    @NotNull(message = "运费模板不能为空")
    private Long        freightTemplateId;

    @ApiModelProperty(name = "weight", value = "商品重量（单位：毫克）")
    @NotNull(message = "商品重量不能为空")
    private Long        weight;

    @ApiModelProperty(name = "h5Detail", value = "商品详情")
    private String      h5Detail;

    @ApiModelProperty(name = "description", value = "商品说明")
    private String      description;

    @ApiModelProperty(name = "skus", value = "单/多规格")
    private List<CreateSkuReqDTO> skus;
}
