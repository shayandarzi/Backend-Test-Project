package io.egroup.backendtestproject.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ExceptionType {
    ENTITY_NOT_FOUND(1000, HttpStatus.UNPROCESSABLE_ENTITY, "io.egroup.backend.error.entity.not.found"),
    INVALID_DATA(1001, HttpStatus.BAD_REQUEST, "io.egroup.backend.error.invalid-data"),
    UNPROCESSABLE_REQUEST(1003, HttpStatus.UNPROCESSABLE_ENTITY, "io.egroup.backend.insufficient.developer");

    private final int code;
    private final HttpStatus httpStatus;
    private final String messageKey;

    ExceptionType(int code, HttpStatus httpStatus, String messageKey) {
        this.code = code;
        this.httpStatus = httpStatus;
        this.messageKey = messageKey;
    }
}
