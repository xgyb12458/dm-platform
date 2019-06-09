package com.damon.shared.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;

/**
 * 分页请求参数
 * @author Damon S.
 */
@Data
public class PageableReqDTO {

    @ApiModelProperty(value = "每页数量")
    @Min(value = 5, message = "每页数量必须大于等于5")
    private Long pageSize;

    @ApiModelProperty(value = "页码")
    @Min(value = 1, message = "页码值必须大于0")
    private Long pageIndex;
}
