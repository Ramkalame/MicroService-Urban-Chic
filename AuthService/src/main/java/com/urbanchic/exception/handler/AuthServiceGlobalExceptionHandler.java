package com.urbanchic.exception.handler;

import com.urbanchic.exception.*;
import com.urbanchic.util.ApiResponse;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class AuthServiceGlobalExceptionHandler {

    @ExceptionHandler(CustomFeignException.class )
    public ResponseEntity<?> handleCustomFeignException(CustomFeignException exception) {
        return ResponseEntity.status(exception.getStatus()).body(exception.getApiResponse());
    }

    @ExceptionHandler(OtpNotFoundException.class )
    public ResponseEntity<?> handleOtpNotFoundException(OtpNotFoundException exception) {
        ApiResponse<?> apiResponse = ApiResponse.builder()
                .data(null)
                .message(exception.getMessage())
                .timestamp(LocalDateTime.now())
                .statusCode(HttpStatus.NOT_FOUND.value())
                .success(false)
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
    }


    @ExceptionHandler(OtpExpiredException.class )
    public ResponseEntity<?> handleOtpExpiredException(OtpExpiredException exception) {
        ApiResponse<?> apiResponse = ApiResponse.builder()
                .data(null)
                .message(exception.getMessage())
                .timestamp(LocalDateTime.now())
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .success(false)
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }


//    @ExceptionHandler(ExpiredJwtException.class )
//    public ResponseEntity<?> handleExpiredJwtException(ExpiredJwtException exception) {
//        ApiResponse<?> apiResponse = ApiResponse.builder()
//                .data(null)
//                .message(exception.getMessage())
//                .timestamp(LocalDateTime.now())
//                .statusCode(HttpStatus.UNAUTHORIZED.value())
//                .success(false)
//                .build();
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiResponse);
//    }

    @ExceptionHandler(MalformedJwtException.class )
    public ResponseEntity<?> handleMalformedJwtException(MalformedJwtException exception) {
        ApiResponse<?> apiResponse = ApiResponse.builder()
                .data(null)
                .message(exception.getMessage())
                .timestamp(LocalDateTime.now())
                .statusCode(HttpStatus.UNAUTHORIZED.value())
                .success(false)
                .build();
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiResponse);
    }

    @ExceptionHandler(SignatureException.class )
    public ResponseEntity<?> handleSignatureException(SignatureException exception) {
        ApiResponse<?> apiResponse = ApiResponse.builder()
                .data(null)
                .message(exception.getMessage())
                .timestamp(LocalDateTime.now())
                .statusCode(HttpStatus.UNAUTHORIZED.value())
                .success(false)
                .build();
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiResponse);
    }



}
