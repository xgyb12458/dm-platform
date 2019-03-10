package com.damon.product.api.dto.req.sku.specification;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * 创建SKU 单项规格请求参数
 * @author Damon S.
 * @version v1.0.1
 * @date 2019年02月27日 23:29
 */
@Data
@ApiModel(value = "创建SKU单项规格")
public class CreateSpecificationReqDTO implements Serializable {
    private static final Long serialVersionUID = 1L;

}
