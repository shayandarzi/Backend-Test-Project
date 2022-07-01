package io.egroup.backendtestproject.web.exception;

import io.egroup.backendtestproject.dto.response.BaseResponse;
import io.egroup.backendtestproject.exception.EntityNotFoundException;
import io.egroup.backendtestproject.exception.ExceptionType;
import io.egroup.backendtestproject.exception.UnProcessableRequestException;
import io.egroup.backendtestproject.web.message.MessageTranslator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;

@ControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class ControllerExceptionHandler {

    private final MessageTranslator messageTranslator;

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<BaseResponse> handleInsuranceException(EntityNotFoundException exception) {
        log.error("EntityNotFoundException occurred: '{}'", exception.toString());

        return ResponseEntity
                .status(exception.getInsuranceExceptionType().getHttpStatus())
                .body(new BaseResponse(BaseResponse.ErrorData.builder()
                        .errorCode(exception.getInsuranceExceptionType().getCode())
                        .message(exception.getMessage())
                        .data(exception.getData())
                        .build()));
    }

    @ExceptionHandler(UnProcessableRequestException.class)
    public ResponseEntity<BaseResponse> handleInsuranceException(UnProcessableRequestException exception) {
        log.error("UnProcessableRequestException occurred: '{}'", exception.toString());

        return ResponseEntity
                .status(exception.getInsuranceExceptionType().getHttpStatus())
                .body(new BaseResponse(BaseResponse.ErrorData.builder()
                        .errorCode(exception.getInsuranceExceptionType().getCode())
                        .message(messageTranslator.getMessage(exception.getInsuranceExceptionType().getMessageKey()))
                        .data(exception.getData())
                        .build()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BaseResponse> validationException(MethodArgumentNotValidException exception) {
        log.error("MethodArgumentNotValidException occurred: '{}'", exception.toString());

        String errorMsg;
        String errorField = null;
        final FieldError fieldError = exception.getBindingResult().getFieldError();
        if (fieldError != null) {
            errorMsg = fieldError.getDefaultMessage();
            errorField = fieldError.getField();
        } else {
            errorMsg = exception.getMessage();
        }
        if (errorMsg != null) {
            String newMsg = messageTranslator.getMessage(ExceptionType.INVALID_DATA.getMessageKey());
            if (StringUtils.hasText(newMsg)) {
                errorMsg = newMsg;
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new BaseResponse(BaseResponse.ErrorData.builder()
                        .errorCode(ExceptionType.INVALID_DATA.getCode())
                        .message(errorMsg)
                        .data(errorField == null ? null : Collections.singletonMap("errorField", errorField))
                        .build()
                ));
    }

}