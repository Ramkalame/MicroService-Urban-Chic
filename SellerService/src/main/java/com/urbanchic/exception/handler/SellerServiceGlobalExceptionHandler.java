package com.urbanchic.exception.handler;

import com.urbanchic.exception.EntityAlreadyExistException;
import com.urbanchic.exception.EntityNotFoundException;
import com.urbanchic.utils.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class SellerServiceGlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        Map<String, Object> responseMap = new HashMap<>();
        List<ObjectError> erroList = exception.getBindingResult().getAllErrors();
        for (ObjectError error : erroList) {
            String fieldName = ((FieldError) error).getField();
            String messageString = error.getDefaultMessage();
            responseMap.put(fieldName, messageString);
        }
        ApiResponse<?> apiResponse = ApiResponse.builder()
                .data(responseMap)
                .message("Provide valid details")
                .timestamp(LocalDateTime.now())
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .success(false)
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }

    @ExceptionHandler(EntityAlreadyExistException.class)
    public ResponseEntity<?> handleAlreadyExistException(EntityAlreadyExistException e){
        ApiResponse<Object> apiResponse = ApiResponse.builder()
                .data(null)
                .message(e.getMessage())
                .timestamp(LocalDateTime.now())
                .statusCode(HttpStatus.CONFLICT.value())
                .success(false)
                .build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);

    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<?> handleNoSuchElementException(NoSuchElementException e){
        ApiResponse<Object> apiResponse = ApiResponse.builder()
                .data(null)
                .message(e.getMessage())
                .timestamp(LocalDateTime.now())
                .statusCode(HttpStatus.NOT_FOUND.value())
                .success(false)
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);

    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleEntityNotFoundException(EntityNotFoundException e){
        ApiResponse<Object> apiResponse = ApiResponse.builder()
                .data(null)
                .message(e.getMessage())
                .timestamp(LocalDateTime.now())
                .statusCode(HttpStatus.NOT_FOUND.value())
                .success(false)
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);

    }


}
