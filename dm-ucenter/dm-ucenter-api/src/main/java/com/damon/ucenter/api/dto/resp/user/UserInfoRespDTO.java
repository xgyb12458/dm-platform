package com.damon.ucenter.api.dto.resp.user;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@Builder
@ApiModel(value = "购物车项添加成功返回结果信息")
public class UserInfoRespDTO implements Serializable {
    private static final Long serialVersionUID = 1L;
}
