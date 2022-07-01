package io.egroup.backendtestproject.exception;

import lombok.ToString;

@ToString(callSuper = true)
public class UnProcessableRequestException extends BaseException {

    public UnProcessableRequestException(String message) {
        super(message);
    }

    @Override
    public ExceptionType getInsuranceExceptionType() {
        return ExceptionType.UNPROCESSABLE_REQUEST;
    }
}
