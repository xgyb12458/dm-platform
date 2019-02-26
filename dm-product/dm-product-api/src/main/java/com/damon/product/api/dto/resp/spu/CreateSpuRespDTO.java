package com.damon.product.api.dto.resp.spu;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * 创建商品返回信息
 * @author Damon S.
 */
@Data
@ApiModel(value = "创建商品返回信息")
@AllArgsConstructor
public class CreateSpuRespDTO implements Serializable {
    private static final Long serialVersionUID = 1L;

    @ApiModelProperty(name = "spuId", value = "商品ID")
    private Long      spuId;
}
