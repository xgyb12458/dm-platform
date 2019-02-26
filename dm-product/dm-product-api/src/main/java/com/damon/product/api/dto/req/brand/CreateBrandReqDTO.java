package com.damon.product.api.dto.req.brand;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * 创建品牌请求
 * @author Damon S.
 * @version v1.0.1
 * @date 2019年02月23日 17:03
 */
@Data
@ApiModel(value = "创建商品品牌请求参数")
public class CreateBrandReqDTO implements Serializable {
    private static final Long serialVersionUID = 1L;

    @ApiModelProperty(name = "name", value = "品牌名称")
    @NotNull(message = "品牌名称不能为空")
    private String      name;

    @ApiModelProperty(name = "code", value = "品牌编码")
    @NotNull(message = "品牌编码不能为空")
    private String      code;

    @ApiModelProperty(name = "logo", value = "品牌LOGO")
    @NotNull(message = "品牌LOGO不能为空")
    private String      logo;

    @ApiModelProperty(name = "display", value = "是否显示(1是，0否)")
    private Integer     display;

    @ApiModelProperty(name = "factoryState", value = "是否为品牌制造商(1是，0否)")
    private Integer     factoryState;

    @ApiModelProperty(name = "sort", value = "排序")
    @Min(value = 0, message = "排序值须大于等于0")
    private Integer      sort;

    @ApiModelProperty(name = "firstLetter", value = "首字母")
    @Pattern(regexp = "[a-zA-Z]{1}", message = "首字母必须为1位英文字母且不为空")
    private String      firstLetter;

    @ApiModelProperty(name = "bigImage", value = "专区大图")
    private String      bigImage;

    @ApiModelProperty(name = "brandStory", value = "品牌故事")
    private String      brandStory;
}
