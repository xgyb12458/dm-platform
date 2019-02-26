package com.damon.product.api.dto.req.spu;

import com.damon.shared.dto.PageableReqDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 查询SPU参数
 * @author Damon S.
 * @version v1.0.1
 * @date 2019年02月24日 23:53
 */
@Data
@ApiModel(value = "创建商品请求参数")
public class QuerySpuReqDTO extends PageableReqDTO implements Serializable {
    private static final Long serialVersionUID = 1L;

    @ApiModelProperty(name = "name", value = "商品名称")
    private String      name;

    @ApiModelProperty(name = "spuCode", value = "商品编码")
    private String      spuCode;

    @ApiModelProperty(name = "type", value = "商品类型(0普通商品，1套装商品)")
    private Integer     type;

    @ApiModelProperty(name = "supportReturn", value = "是否支持退货(1是，0否)")
    private Integer     supportReturn;

    @ApiModelProperty(name = "categoryId", value = "类别")
    private Long        categoryId;

    @ApiModelProperty(name = "brandId", value = "品牌")
    private Long        brandId;

    @ApiModelProperty(name = "warehouseId", value = "仓库")
    private Long        warehouseId;

    @ApiModelProperty(name = "supplierId", value = "供应商")
    private Long        supplierId;

    @ApiModelProperty(name = "verifyState", value = "审核状态(0草稿，1审核中，2通过，3驳回)")
    private Integer     verifyState;

    @ApiModelProperty(name = "state", value = "商品状态(0草稿箱,1计划发布,2上架,3下架)")
    private Integer     state;

    @ApiModelProperty(name = "deliveryRegion", value = "配送区域")
    private String      deliveryRegion;

    @ApiModelProperty(name = "createdFrom", value = "创建时间-从(毫秒数)")
    private Long        createdFrom;

    @ApiModelProperty(name = "createdTo", value = "创建时间-到(毫秒数)")
    private Long        createdTo;
}
