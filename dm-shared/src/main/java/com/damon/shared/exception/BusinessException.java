package com.damon.shared.exception;

import com.damon.shared.enums.ResponseCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Value;

/**
 * @author Damon S.
 */
@Value
@AllArgsConstructor
public class BusinessException extends RuntimeException {

    private final Integer code;

    public BusinessException(ResponseCodeEnum codeEnum) {
        super(codeEnum.getMessage());
        this.code = codeEnum.getCode();
    }

    public BusinessException(ResponseCodeEnum codeEnum, String message) {
        super(codeEnum.getMessage() + "：" + message);
        this.code = codeEnum.getCode();
    }

    public BusinessException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(Integer code, Throwable cause) {
        super(cause);
        this.code = code;
    }

    public BusinessException(Integer code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }
}

