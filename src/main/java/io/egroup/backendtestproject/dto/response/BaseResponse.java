package io.egroup.backendtestproject.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

@Data
public class BaseResponse implements Serializable {

    private boolean successful;
    private ErrorData errorData;

    public BaseResponse() {
    }

    public BaseResponse(ErrorData errorData) {
        this.successful = false;
        this.errorData = errorData;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ErrorData implements Serializable {
        private int errorCode;
        private String message;
        private Map<String, Object> data;
    }

}
