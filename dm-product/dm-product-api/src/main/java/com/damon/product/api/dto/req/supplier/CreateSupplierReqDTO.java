package com.damon.product.api.dto.req.supplier;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 创建供应商
 * @author Damon S.
 * @version v1.0.1
 * @date 2019年02月23日 17:03
 */
@Data
@ApiModel(value = "创建供应商")
public class CreateSupplierReqDTO implements Serializable {
    private static final Long serialVersionUID = 1L;

    @ApiModelProperty(value = "供应商名称")
    private String      name;

    @ApiModelProperty(value = "供应商编码")
    private String      code;

    @ApiModelProperty(value = "关联仓库")
    private List<Long>  warehouseId;
}
