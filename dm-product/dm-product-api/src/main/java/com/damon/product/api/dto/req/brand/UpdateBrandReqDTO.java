package com.damon.product.api.dto.req.brand;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 编辑品牌
 * @author Damon S.
 * @version v1.0.1
 * @date 2019年02月23日 17:19
 */
@Data
@ApiModel(value = "编辑品牌")
public class UpdateBrandReqDTO implements Serializable {
    private static final Long serialVersionUID = 1L;

    @ApiModelProperty(value = "品牌ID")
    @NotNull(message = "品牌ID不能为空")
    private Long        brandId;

    @ApiModelProperty(value = "品牌名称")
    private String      name;

    @ApiModelProperty(value = "品牌编码")
    private String      code;

    @ApiModelProperty(value = "品牌LOGO")
    private String      logo;

    @ApiModelProperty(value = "是否显示(1是，0否)")
    private Integer     display;

    @ApiModelProperty(value = "是否为品牌制造商(1是，0否)")
    private Integer     factoryState;

    @ApiModelProperty(value = "排序")
    private Integer     sort;

    @ApiModelProperty(value = "首字母")
    private String      firstLetter;

    @ApiModelProperty(value = "专区大图")
    private String      bigImage;

    @ApiModelProperty(value = "品牌故事")
    private String      brandStory;
}
