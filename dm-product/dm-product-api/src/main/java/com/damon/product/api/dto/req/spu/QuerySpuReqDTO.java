package com.damon.product.api.dto.req.spu;

import com.damon.product.shared.enums.ProductType;
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
public class QuerySpuReqDTO implements Serializable {
    private static final Long serialVersionUID = 1L;

    @ApiModelProperty(name = "name", value = "商品名称")
    private String      name;

    @ApiModelProperty(name = "spuCode", value = "商品编码")
    private String      spuCode;

    @ApiModelProperty(name = "model", value = "型号")
    private String      model;

    @ApiModelProperty(name = "type", value = "商品类型")
    private ProductType type;

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
}
