package com.damon.product.api.dto.req.category;

import com.damon.shared.dto.PageableReqDTO;
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
public class QueryCategoryReqDTO extends PageableReqDTO implements Serializable {
    private static final Long serialVersionUID = 1L;

    @ApiModelProperty(name = "name", value = "品类名称")
    private String      name;

    @ApiModelProperty(name = "navState", value = "是否显示在导航栏")
    private String      navState;

    @ApiModelProperty(name = "showState", value = "显示状态")
    private String      showState;

    @ApiModelProperty(name = "keywords", value = "关键字")
    private String      keywords;

    @ApiModelProperty(name = "parentId", value = "上级分类的编号：0表示一级分类")
    private Long        parentId;
}
