package com.damon.product.api.dto.req.brand;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * 查询商品品牌
 * @author Damon S.
 * @version v1.0.1
 * @date 2019年02月23日 17:14
 */
@Data
@ApiModel(value = "查询商品品牌请求参数")
public class QueryBrandReqDTO implements Serializable {
    private static final Long serialVersionUID = 1L;

    @ApiModelProperty(name = "name", value = "品牌名称")
    private String      name;

    @ApiModelProperty(name = "code", value = "品牌编码")
    private String      code;

    @ApiModelProperty(name = "display", value = "是否显示(1是，0否)")
    private Integer     display;

    @ApiModelProperty(name = "factoryState", value = "是否为品牌制造商(1是，0否)")
    private Integer     factoryState;

    @ApiModelProperty(name = "deleted", value = "是否已删除(1是，0否)")
    private Integer     deleted;

    @ApiModelProperty(name = "firstLetter", value = "首字母")
    private String      firstLetter;

    @ApiModelProperty(name = "createdFrom", value = "创建日期-从（yyyy-MM-dd）")
    @Pattern(regexp = "^\\d{4}-\\d{1,2}-\\d{1,2}$", message = "日期格式需满足yyyy-MM-dd")
    private String      createdFrom;

    @ApiModelProperty(name = "createdTo", value = "创建日期-到（yyyy-MM-dd）")
    @Pattern(regexp = "^\\d{4}-\\d{1,2}-\\d{1,2}$", message = "日期格式需满足yyyy-MM-dd")
    private String      createdTo;
}
