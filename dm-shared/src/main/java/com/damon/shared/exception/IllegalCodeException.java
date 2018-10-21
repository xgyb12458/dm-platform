package com.damon.shared.exception;

/**
 * 非法操作系统编码
 * @author Damon S.
 */
public class IllegalCodeException extends RuntimeException {
    public IllegalCodeException() {
        super();
    }

    public IllegalCodeException(String message) {
        super(message);
    }
}
