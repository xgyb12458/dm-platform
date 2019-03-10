package com.damon.product.api.dto.req.warehouse;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 编辑商品类别
 * @author Damon S.
 * @version v1.0.1
 * @date 2019年02月23日 17:19
 */
@Data
@ApiModel(value = "编辑商品类别")
public class UpdateWarehouseReqDTO implements Serializable {
    private static final Long serialVersionUID = 1L;

    @ApiModelProperty(value = "品牌ID")
    private Long        brandId;

    @ApiModelProperty(value = "品牌名称")
    private String      name;

    @ApiModelProperty(value = "品牌编码")
    private String      code;

    @ApiModelProperty(value = "品牌LOGO")
    private String      logo;

    @ApiModelProperty(value = "是否显示")
    private Boolean     display;

    @ApiModelProperty(value = "是否为品牌制造商")
    private Boolean     factoryState;

    @ApiModelProperty(value = "排序")
    private Integer     sort;

    @ApiModelProperty(value = "首字母")
    private String      firstLetter;

    @ApiModelProperty(value = "专区大图")
    private String      bigImage;

    @ApiModelProperty(value = "品牌故事")
    private String      brandStory;
}
