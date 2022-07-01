package io.egroup.backendtestproject.exception;

import lombok.ToString;

@ToString(callSuper = true)
public class EntityNotFoundException extends BaseException {

    public EntityNotFoundException(String message) {
        super(message);
    }

    @Override
    public ExceptionType getInsuranceExceptionType() {
        return ExceptionType.ENTITY_NOT_FOUND;
    }
}
