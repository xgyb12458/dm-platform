package com.damon.oauth.manager.dto.resp.resource;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @author Damon
 */
@Data
@ToString
public class ResourceInfoRespDTO {


    @ApiModelProperty(value = "资源ID")
    private String resourceId;


    @ApiModelProperty(value = "资源编码")
    private String code;


    @ApiModelProperty(value = "资源名称")
    private String name;
}
