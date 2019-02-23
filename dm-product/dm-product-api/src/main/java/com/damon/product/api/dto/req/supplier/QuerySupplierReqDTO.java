package com.damon.product.api.dto.req.supplier;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 查询商品类别
 * @author Damon S.
 * @version v1.0.1
 * @date 2019年02月23日 17:14
 */
@Data
@ApiModel(value = "查询商品类别")
public class QuerySupplierReqDTO implements Serializable {
    private static final Long serialVersionUID = 1L;

    @ApiModelProperty(name = "name", value = "品牌名称")
    private String      name;

    @ApiModelProperty(name = "code", value = "品牌编码")
    private String      code;

    @ApiModelProperty(name = "display", value = "是否显示")
    private Boolean     display;

    @ApiModelProperty(name = "factoryState", value = "是否为品牌制造商")
    private Boolean     factoryState;

    @ApiModelProperty(name = "deleted", value = "是否已删除")
    private Boolean     deleted;

    @ApiModelProperty(name = "firstLetter", value = "首字母")
    private String      firstLetter;

    @ApiModelProperty(name = "createdFrom", value = "创建时间-从")
    private String   createdFrom;

    @ApiModelProperty(name = "createdTo", value = "创建时间-到")
    private String   createdTo;
}
