package com.damon.ucenter.api.dto.resp.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author Damon S.
 */
@Data
@ToString
@Builder
@ApiModel(value = "购物车项信息")
public class UpdateUserRespDTO implements Serializable {
    private static final Long serialVersionUID = 1L;

    @ApiModelProperty(value = "购物车项Id")
    private Long cid;

    @ApiModelProperty(value = "商品SkuId")
    private Long skuid;

    @ApiModelProperty(value = "购买数量")
    private Integer qty;

    @ApiModelProperty(value = "活动编号")
    private Long pid;

    @ApiModelProperty(value = "活动详情编号")
    private Long did;
}
