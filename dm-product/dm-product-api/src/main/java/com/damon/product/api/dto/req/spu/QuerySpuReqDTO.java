package com.damon.product.api.dto.req.spu;

import com.damon.shared.dto.PageableReqDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;

/**
 * 查询商品
 * @author Damon S.
 * @version v1.0.1
 * @date 2019年02月24日 23:53
 */
@Data
@ApiModel(value = "查询商品")
public class QuerySpuReqDTO extends PageableReqDTO implements Serializable {
    private static final Long serialVersionUID = 1L;

    @ApiModelProperty(value = "商品名称")
    private String      name;

    @ApiModelProperty(value = "商品编码")
    private String      spuCode;

    @ApiModelProperty(value = "商品类型(0普通商品，1套装商品)", allowableValues = "range[0,1]")
    @Min(value = 0, message = "type取值不合法")
    @Max(value = 1, message = "type取值不合法")
    private Integer     type;

    @ApiModelProperty(value = "是否支持退货(1是，0否)", allowableValues = "range[0,1]")
    @Min(value = 0, message = "supportReturn取值不合法")
    @Max(value = 1, message = "supportReturn取值不合法")
    private Integer     supportReturn;

    @ApiModelProperty(value = "审核状态(0草稿，1审核中，2通过，3驳回)", allowableValues = "range[0,3]")
    @Min(value = 0, message = "verifyState取值不合法")
    @Max(value = 3, message = "verifyState取值不合法")
    private Integer     verifyState;

    @ApiModelProperty(value = "商品状态(0草稿箱,1计划发布,2上架,3下架)", allowableValues = "range[0,3]")
    @Min(value = 0, message = "state取值不合法")
    @Max(value = 3, message = "state取值不合法")
    private Integer     state;

    @ApiModelProperty(value = "配送区域")
    private String      deliveryRegion;

    @ApiModelProperty(value = "类别")
    private Long        categoryId;

    @ApiModelProperty(value = "品牌")
    private Long        brandId;

    @ApiModelProperty(value = "仓库")
    private Long        warehouseId;

    @ApiModelProperty(value = "供应商")
    private Long        supplierId;

    @ApiModelProperty(value = "创建时间-从(毫秒数)")
    @Min(value = 1551161592000L, message = "开始时间毫秒数不合法")
    @Max(value = 2177424000000L, message = "开始时间毫秒数不合法")
    private Long        createdFrom;

    @ApiModelProperty(value = "创建时间-到(毫秒数)")
    @Min(value = 1551161592000L, message = "结束时间毫秒数不合法")
    @Max(value = 2177424000000L, message = "结束时间毫秒数不合法")
    private Long        createdTo;
}
