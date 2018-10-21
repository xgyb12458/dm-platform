package com.damon.shared.exception;

/**
 * 聚合域的ID为空异常
 * @author Damon S.
 */
public class NullIdException extends RuntimeException {

    public NullIdException() {
        super();
    }

    public NullIdException(String message) {
        super(message);
    }
}
