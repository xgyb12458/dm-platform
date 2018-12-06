package com.damon.oauth.api.dto.resp.resource;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @author Damon
 */
@Data
@ToString
public class ResourceInfoRespDTO {


    @ApiModelProperty(name = "resourceId", value = "资源ID")
    private String resourceId;


    @ApiModelProperty(name = "code", value = "资源编码")
    private String code;


    @ApiModelProperty(name = "name", value = "资源名称")
    private String name;
}
