package com.urbanchic.controller;

import com.urbanchic.dto.CreateUserDto;
import com.urbanchic.service.UserService;
import com.urbanchic.service.impl.UserServiceImpl;
import com.urbanchic.util.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping(value = "/create")
    public ResponseEntity<?> createUser(@RequestBody @Valid CreateUserDto createUserDto) {
        String responseData = userService.createUser(createUserDto);
        ApiResponse<Object> apiResponse = ApiResponse.builder()
                .data(responseData)
                .message("Created")
                .timestamp(LocalDateTime.now())
                .statusCode(HttpStatus.CREATED.value())
                .success(true)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

}
