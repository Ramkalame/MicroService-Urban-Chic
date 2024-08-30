//package com.urbanchic.exception.handler;
//
//import com.urbanchic.exception.MissingAuthorizationHeaderException;
//import com.urbanchic.exception.MissingAuthorizationTokenException;
//import com.urbanchic.exception.UnAuthorizeAccessToApplicationException;
//import com.urbanchic.util.ApiResponse;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//
//import java.time.LocalDateTime;
//
//@ControllerAdvice
//public class GlobalGatewayExceptionHandler {
//
//    @ExceptionHandler(MissingAuthorizationHeaderException.class)
//    public ResponseEntity<?> handleMissingAuthorizationHeaderException(MissingAuthorizationHeaderException exception){
//        ApiResponse<?> apiResponse = ApiResponse.builder()
//                .data(null)
//                .message(exception.getMessage())
//                .timestamp(LocalDateTime.now())
//                .statusCode(HttpStatus.UNAUTHORIZED.value())
//                .success(false)
//                .build();
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiResponse);
//    }
//
//    @ExceptionHandler(MissingAuthorizationTokenException.class)
//    public ResponseEntity<?> handleMissingAuthorizationTokenException(MissingAuthorizationTokenException exception){
//        ApiResponse<?> apiResponse = ApiResponse.builder()
//                .data(null)
//                .message(exception.getMessage())
//                .timestamp(LocalDateTime.now())
//                .statusCode(HttpStatus.UNAUTHORIZED.value())
//                .success(false)
//                .build();
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiResponse);
//    }
//
//    @ExceptionHandler(UnAuthorizeAccessToApplicationException.class)
//    public ResponseEntity<?> handleUnAuthorizeAccessToApplicationException(UnAuthorizeAccessToApplicationException exception){
//        ApiResponse<?> apiResponse = ApiResponse.builder()
//                .data(null)
//                .message(exception.getMessage())
//                .timestamp(LocalDateTime.now())
//                .statusCode(HttpStatus.UNAUTHORIZED.value())
//                .success(false)
//                .build();
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiResponse);
//    }
//
//}
