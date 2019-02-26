package com.damon.shared.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;

/**
 * 分页请求
 * @author Damon S.
 */
@Data
@ApiModel(value = "分页请求参数")
public class PageableReqDTO {

    @ApiModelProperty(name = "pageSize", value = "每页数量")
    @Min(value = 6, message = "每页数量必须大于等于6")
    private Long pageSize;

    @ApiModelProperty(name = "pageIndex", value = "页码")
    @Min(value = 1, message = "页码值必须大于0")
    private Long pageIndex;
}
