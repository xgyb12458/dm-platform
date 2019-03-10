package com.damon.product.api.dto.resp.category;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 商品类别信息
 * @author Damon S.
 * @version v1.0.1
 * @date 2019年02月23日 21:21
 */
@Data
@ApiModel(value = "获取商品类别信息")
public class CategoryInfoRespDTO implements Serializable {
    private static final Long serialVersionUID = 1L;

    @ApiModelProperty(value = "品类Id")
    @NotNull
    private Long        categoryId;

    @ApiModelProperty(value = "品类名称")
    private String      name;

    @ApiModelProperty(value = "品类级别")
    private Integer     level;

    @ApiModelProperty(value = "图标")
    private String      icon;

    @ApiModelProperty(value = "商品数量")
    private Integer     spuCount;

    @ApiModelProperty(value = "数量单位")
    private String      spuUnit;

    @ApiModelProperty(value = "排序")
    private Integer     sort;

    @ApiModelProperty(value = "是否显示在导航栏")
    private Integer      navState;

    @ApiModelProperty(value = "显示状态")
    private Integer      showState;

    @ApiModelProperty(value = "关键字")
    private String      keywords;

    @ApiModelProperty(value = "上级分类的编号：0表示一级分类")
    private Long        parentId;

    @ApiModelProperty(value = "描述信息")
    private String      description;

    @ApiModelProperty(value = "创建人")
    private Long      createdBy;

    @ApiModelProperty(value = "创建时间")
    private Long      createdAt;

    @ApiModelProperty(value = "最近一次更新人")
    private Long      updatedBy;

    @ApiModelProperty(value = "最近一次更新时间")
    private Long      updatedAt;
}
