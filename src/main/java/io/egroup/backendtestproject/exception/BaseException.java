package io.egroup.backendtestproject.exception;

import java.util.Map;

public abstract class BaseException extends RuntimeException {

    private Map<String, Object> data;

    protected BaseException(String message, Map<String, Object> data) {
        super(message);
        this.data = data;
    }

    protected BaseException(String message) {
        super(message);
    }

    public abstract ExceptionType getInsuranceExceptionType();

    public Map<String, Object> getData() {
        return this.data;
    }
}

