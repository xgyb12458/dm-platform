package com.damon.media.api.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;

/**
 * @author Damon S.
 */
@Data
@ApiModel(value = "分页请求参数")
public class PageableReqDTO {

    @Min(value = 1, message = "页码必须大于等于1")
    @ApiModelProperty(name = "pageIndex", value = "页码")
    private Integer pageIndex;

    @Min(value = 1, message = "每页数量必须大于等于1")
    @ApiModelProperty(name = "pageSize", value = "每页数量")
    private Integer pageSize;
}
