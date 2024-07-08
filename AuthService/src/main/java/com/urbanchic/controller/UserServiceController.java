package com.urbanchic.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.urbanchic.entity.Otp;
import com.urbanchic.util.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;


@RestController
@RequestMapping("/users")
public class UserServiceController {

    @GetMapping("/verify/role")
    public ResponseEntity<ApiResponse<Boolean>> verifyRole(){

         ApiResponse<Boolean> apiResponse = ApiResponse.<Boolean>builder()
                .data(true)
                .message("User authorized successfully")
                .timestamp(LocalDateTime.now())
                .statusCode(HttpStatus.OK.value())
                .success(true)
                .build();
         return ResponseEntity.ok(apiResponse);
    }

}
