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
@ApiModel(value = "创建商品")
public class CreateSpuReqDTO implements Serializable {
    private static final Long serialVersionUID = 1L;

    @ApiModelProperty(value = "商品名称")
    @NotBlank(message = "商品名称不能为空")
    private String      name;

    @ApiModelProperty(value = "商品副标题")
    @NotBlank(message = "副标题不能为空")
    private String      subTitle;

    @ApiModelProperty(value = "商品编码")
    @NotBlank(message = "商品编码不能为空")
    private String      spuCode;

    @ApiModelProperty(value = "主图")
    @NotNull(message = "主图不能为空")
    private Long        imageId;

    @ApiModelProperty(value = "商品图片")
    private List<Long>  albumImages;

    @ApiModelProperty(value = "售价（单位：分）")
    @NotNull(message = "售价不能为空")
    private Long        price;

    @ApiModelProperty(value = "市场价（单位：分）")
    private Long        marketPrice;

    @ApiModelProperty(value = "库存")
    @Min(value = 0, message = "库存不合法")
    private Integer     inventory;

    @ApiModelProperty(value = "安全库存")
    @Min(value = 0, message = "安全库存不合法")
    private Integer     safetyStock;

    @ApiModelProperty(value = "型号")
    private String      model;

    @ApiModelProperty(value = "商品类型(0普通商品，1套装商品)")
    @Min(value = 0, message = "type取值不合法")
    @Max(value = 1, message = "type取值不合法")
    private Integer     type;

    @ApiModelProperty(value = "是否支持退货(1是，0否)")
    @Min(value = 0, message = "supportReturn取值不合法")
    @Max(value = 1, message = "supportReturn取值不合法")
    private Integer     supportReturn;

    @ApiModelProperty(value = "配送区域")
    @NotBlank(message = "配送区域不能为空")
    private String      deliveryRegion;

    @ApiModelProperty(value = "是否新品(1是，0否)")
    @Min(value = 0, message = "newProduct取值不合法")
    @Max(value = 1, message = "newProduct取值不合法")
    private Integer     newProduct;

    @ApiModelProperty(value = "是否推荐(1是，0否)")
    @Min(value = 0, message = "recommended取值不合法")
    @Max(value = 1, message = "recommended取值不合法")
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
    @NotNull(message = "运费模板不能为空")
    private Long        freightTemplateId;

    @ApiModelProperty(value = "商品重量（单位：毫克）")
    @NotNull(message = "商品重量不能为空")
    private Long        weight;

    @ApiModelProperty(value = "商品详情")
    private String      h5Detail;

    @ApiModelProperty(value = "商品说明")
    private String      description;

    @ApiModelProperty(value = "单/多规格")
    private List<CreateSkuReqDTO> skus;
}
