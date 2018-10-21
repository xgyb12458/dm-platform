package com.damon.shared.exception;

import com.damon.shared.enums.ResponseCodeEnum;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Damon S.
 */
@ControllerAdvice
public class HdsExceptionHandler {
    @ResponseStatus(value= HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(BusinessException.class)
    public ExceptionResult notExpected(BusinessException e) {
        return new ExceptionResult(e.getCode(), e.getMessage(), BusinessException.class);
    }

    @ResponseStatus(value= HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(SystemException.class)
    public ExceptionResult notExpected(SystemException e) {
        return new ExceptionResult(e.getCode(), e.getMessage(), SystemException.class);
    }

    @ResponseStatus(value= HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler()
    public ExceptionResult outOfControl(Throwable t) {
        return new ExceptionResult(ResponseCodeEnum.INTERNAL_ERROR.getCode(), t.getMessage(), t.getClass());
    }

    @Getter
    private static class ExceptionResult {
        private Integer code;
        private String message;
        private String throwableName;

        private ExceptionResult(Integer code, String message, Class<? extends Throwable> clazz) {
            this.code = code;
            this.message = message;
            this.throwableName = clazz.getName();
        }
    }
}


