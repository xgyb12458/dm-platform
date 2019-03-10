package com.damon.product.api.dto.req.warehouse;

import com.damon.shared.dto.PageableReqDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 查询仓库
 * @author Damon S.
 * @version v1.0.1
 * @date 2019年02月23日 17:14
 */
@Data
@ApiModel(value = "查询仓库")
public class QueryWarehouseReqDTO extends PageableReqDTO implements Serializable {
    private static final Long serialVersionUID = 1L;

    @ApiModelProperty(value = "品牌名称")
    private String      name;

    @ApiModelProperty(value = "品牌编码")
    private String      code;

    @ApiModelProperty(value = "是否显示")
    private Boolean     display;

    @ApiModelProperty(value = "是否为品牌制造商")
    private Boolean     factoryState;

    @ApiModelProperty(value = "是否已删除")
    private Boolean     removed;

    @ApiModelProperty(value = "首字母")
    private String      firstLetter;

    @ApiModelProperty(value = "创建时间-从")
    private String   createdFrom;

    @ApiModelProperty(value = "创建时间-到")
    private String   createdTo;
}
