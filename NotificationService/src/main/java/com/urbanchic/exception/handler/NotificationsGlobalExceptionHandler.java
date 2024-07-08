package com.urbanchic.exception.handler;

import com.urbanchic.exception.SmsNotSentException;
import com.urbanchic.util.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class NotificationsGlobalExceptionHandler {

    @ExceptionHandler(SmsNotSentException.class)
    public ResponseEntity<?> handleSmsNotSentException(SmsNotSentException exception){
        ApiResponse<?> apiResponse = ApiResponse.builder()
                .data(null)
                .message(exception.getMessage())
                .timestamp(LocalDateTime.now())
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .success(false)
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }

}
