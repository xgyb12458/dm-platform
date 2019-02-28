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

    @ApiModelProperty(name = "categoryId", value = "品类Id")
    @NotNull
    private Long        categoryId;

    @ApiModelProperty(name = "name", value = "品类名称")
    private String      name;

    @ApiModelProperty(name = "level", value = "品类级别")
    private String      level;

    @ApiModelProperty(name = "icon", value = "图标")
    private String      icon;

    @ApiModelProperty(name = "spuCount", value = "商品数量")
    private Integer     spuCount;

    @ApiModelProperty(name = "spuUnit", value = "数量单位")
    private String      spuUnit;

    @ApiModelProperty(name = "sort", value = "排序")
    private Integer     sort;

    @ApiModelProperty(name = "navState", value = "是否显示在导航栏")
    private Integer      navState;

    @ApiModelProperty(name = "showState", value = "显示状态")
    private Integer      showState;

    @ApiModelProperty(name = "keywords", value = "关键字")
    private String      keywords;

    @ApiModelProperty(name = "parentId", value = "上级分类的编号：0表示一级分类")
    private Long        parentId;

    @ApiModelProperty(name = "description", value = "描述信息")
    private String      description;

    @ApiModelProperty(name = "createdBy", value = "创建人")
    private Long      createdBy;

    @ApiModelProperty(name = "createdAt", value = "创建时间")
    private Long      createdAt;

    @ApiModelProperty(name = "updatedBy", value = "最近一次更新人")
    private Long      updatedBy;

    @ApiModelProperty(name = "updatedAt", value = "最近一次更新时间")
    private Long      updatedAt;
}
