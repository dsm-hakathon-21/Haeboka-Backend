package com.hackathon.dsmhackathon21.global.error;

public class CustomException extends RuntimeException {
    private final Error error;

    public Error getError() {
        return error;
    }

    protected CustomException(Error error) {
        this.error = error;
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
