package com.damon.product.api.dto.resp.spu;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 创建商品返回信息
 * @author Damon S.
 */
@Data
@ApiModel(value = "创建商品参数")
public class CreateProductRespDTO implements Serializable {
    private static final Long serialVersionUID = 1L;

    @ApiModelProperty(name = "name", value = "商品名称")
    private String      name;
}
