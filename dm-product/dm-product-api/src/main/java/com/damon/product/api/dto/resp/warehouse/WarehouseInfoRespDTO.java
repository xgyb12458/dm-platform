package com.damon.product.api.dto.resp.warehouse;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * 商品类别信息
 * @author Damon S.
 * @version v1.0.1
 * @date 2019年02月23日 21:21
 */
@Data
@ApiModel(value = "获取商品类别信息")
public class WarehouseInfoRespDTO implements Serializable {
    private static final Long serialVersionUID = 1L;
}
