package com.example.demo.lambda;

/**
 * @author caozhixin
 * @date 2023/12/22 15:55
 */

public final class StopException extends RuntimeException {
    public static final StopException INSTANCE = new StopException();

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
