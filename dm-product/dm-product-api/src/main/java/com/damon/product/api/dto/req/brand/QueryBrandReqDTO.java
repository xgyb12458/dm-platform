package com.damon.product.api.dto.req.brand;

import com.damon.shared.dto.PageableReqDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;

/**
 * 查询商品品牌
 * @author Damon S.
 * @version v1.0.1
 * @date 2019年02月23日 17:14
 */
@Data
@ApiModel(value = "查询品牌")
public class QueryBrandReqDTO extends PageableReqDTO implements Serializable {
    private static final Long serialVersionUID = 1L;

    @ApiModelProperty(value = "品牌名称")
    private String      name;

    @ApiModelProperty(value = "品牌编码")
    private String      code;

    @ApiModelProperty(value = "是否为品牌制造商(1是，0否)", allowableValues = "range=[0,1]")
    private Integer     factoryState;

    @ApiModelProperty(value = "是否已删除(1是，0否)", allowableValues = "range=[0,1]")
    private Integer     removed;

    @ApiModelProperty(value = "首字母")
    private String      firstLetter;

    @ApiModelProperty(value = "创建日期-从（毫秒数）")
    @Min(value = 1551161592000L, message = "开始时间毫秒数不合法")
    @Max(value = 2177424000000L, message = "开始时间毫秒数不合法")
    private Long      createdFrom;

    @ApiModelProperty(value = "创建日期-到（毫秒数）")
    @Min(value = 1551161592000L, message = "开始时间毫秒数不合法")
    @Max(value = 2177424000000L, message = "开始时间毫秒数不合法")
    private Long      createdTo;
}
