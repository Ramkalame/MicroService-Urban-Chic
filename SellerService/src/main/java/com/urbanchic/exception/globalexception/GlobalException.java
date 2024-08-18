package com.urbanchic.exception.globalexception;

import com.urbanchic.exception.SellerAlreadyExistException;
import com.urbanchic.exception.SellerNotFoundException;
import com.urbanchic.utils.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodLevelArgument(MethodArgumentNotValidException e){
        ApiResponse<Object> apiResponse = ApiResponse.builder()
                .data(null)
                .message(e.getMessage())
                .timestamp(LocalDateTime.now())
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .success(false)
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);

    }

    @ExceptionHandler(SellerAlreadyExistException.class)
    public ResponseEntity<?> handleAlreadyExistException(SellerAlreadyExistException e){
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

    @ExceptionHandler(SellerNotFoundException.class)
    public ResponseEntity<?> handleSellerNotFoundException(SellerNotFoundException e){
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
