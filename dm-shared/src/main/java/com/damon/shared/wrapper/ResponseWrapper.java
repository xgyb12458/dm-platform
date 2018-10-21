package com.damon.shared.wrapper;

import com.damon.shared.enums.ResponseCodeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * @author Damon S.
 * @param <T> 返回数据类型
 */
@Getter
@ApiModel(value = "响应信息")
public class ResponseWrapper<T> {
    @ApiModelProperty(value = "返回码", dataType = "Integer")
    private final Integer code;

    @ApiModelProperty(value = "信息", dataType = "string")
    private final String message;

    @ApiModelProperty(value = "数据")
    private final T data;

    public ResponseWrapper() {
        this(ResponseCodeEnum.SUCCESS.getCode(), ResponseCodeEnum.SUCCESS.getMessage(), null);
    }

    public ResponseWrapper(T data) {
        this(ResponseCodeEnum.SUCCESS.getCode(), ResponseCodeEnum.SUCCESS.getMessage(), data);
    }

    public ResponseWrapper(ResponseCodeEnum code) {
        this(code.getCode(), code.getMessage(), null);
    }

    public ResponseWrapper(Integer code, String message) {
        this(code, message, null);
    }

    private ResponseWrapper(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
