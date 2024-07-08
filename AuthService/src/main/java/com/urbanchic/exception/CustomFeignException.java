package com.urbanchic.exception;

import com.urbanchic.util.ApiResponse;
import org.springframework.http.HttpStatus;

public class CustomFeignException extends RuntimeException{

    private final ApiResponse<?> apiResponse;
    private final HttpStatus status;

    public CustomFeignException(ApiResponse<?> apiResponse, HttpStatus status) {
        super((String) apiResponse.getMessage());
        this.apiResponse = apiResponse;
        this.status = status;
    }

    public ApiResponse<?> getApiResponse() {
        return apiResponse;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
