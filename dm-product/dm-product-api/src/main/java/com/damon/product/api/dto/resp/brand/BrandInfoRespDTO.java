package com.damon.product.api.dto.resp.brand;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 品牌信息
 * @author Damon S.
 * @version v1.0.1
 * @date 2019年02月23日 17:12
 */
@Data
@ApiModel(value = "品牌信息")
public class BrandInfoRespDTO implements Serializable {
    private static final Long serialVersionUID = 1L;

    @ApiModelProperty(value = "品牌ID")
    private String      brandId;

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

    @ApiModelProperty(value = "是否已删除")
    private Boolean     deleted;

    @ApiModelProperty(value = "排序")
    private String      sort;

    @ApiModelProperty(value = "首字母")
    private String      firstLetter;

    @ApiModelProperty(value = "专区大图")
    private String      bigImage;

    @ApiModelProperty(value = "品牌故事")
    private String      brandStory;

    @ApiModelProperty(value = "创建人")
    private Long        createdBy;

    @ApiModelProperty(value = "最近一次修改人")
    private Long        updatedBy;

    @ApiModelProperty(value = "创建时间")
    private Long      createdAt;

    @ApiModelProperty(value = "最近一次修改时间")
    private Long      updatedAt;
}
