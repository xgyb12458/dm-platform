package com.damon.shared.exception;

import com.damon.shared.enums.ResponseCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Damon S.
 */
@Getter
@AllArgsConstructor
public class SystemException extends RuntimeException {

    private final Integer code;

    public SystemException(Throwable cause) {
        super(cause);
        this.code = ResponseCodeEnum.INTERNAL_ERROR.getCode();
    }

    public SystemException(String message) {
        super(message);
        this.code = ResponseCodeEnum.INTERNAL_ERROR.getCode();
    }

    public SystemException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public SystemException(Integer code, Throwable cause) {
        super(cause);
        this.code = code;
    }

    public SystemException(Integer code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }
}

