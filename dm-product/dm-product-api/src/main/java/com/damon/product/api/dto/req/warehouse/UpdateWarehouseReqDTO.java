package com.damon.product.api.dto.req.warehouse;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 编辑仓库
 * @author Damon S.
 * @version v1.0.1
 * @date 2019年02月23日 17:19
 */
@Data
@ApiModel(value = "编辑仓库")
public class UpdateWarehouseReqDTO implements Serializable {
    private static final Long serialVersionUID = 1L;

    @ApiModelProperty(value = "仓库ID")
    private Long        warehouseId;

    @ApiModelProperty(value = "仓库名称")
    private String      name;

    @ApiModelProperty(value = "仓库编码")
    private String      code;
}
