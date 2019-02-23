package com.damon.shared.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 系统错误码 说明
 * 系统状态码由五位数字构成，0到10000为预留通用状态码,在该类中申明,其他模块自定义的在各个模块中申明，10000到19999位广告主相关状态码
 * ，20000到29999位资金相关状态码， 30000到39999为竞价相关状态码，40000到49999为Connector相关状态码，50000到59999为Monitor相关状态码。其余暂未分配
 * @author Damon S.
 */
@Getter
@AllArgsConstructor
public enum ResponseCodeEnum {
    /*** 状态成功 **/
    SUCCESS(2000, "成功"),
    AUTH_FAIL(3004, "鉴权失败"),
    BAD_REQUEST(4000, "请求参数不合法"),
    INVALID_TOKEN(4001, "token失效"),
    INTERNAL_ERROR(5000, "服务器异常"),
    NOT_FOUND(5010, "未找到指定对象"),
    BIZ_ERROR(5020, "业务逻辑异常");

    private final Integer code;
    private final String message;
}

