package com.urbanchic.controller;

import com.urbanchic.dto.CreateUserDto;
import com.urbanchic.entity.User;
import com.urbanchic.service.UserService;
import com.urbanchic.service.impl.UserServiceImpl;
import com.urbanchic.util.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody @Valid CreateUserDto createUserDto) {
        CreateUserDto responseData = userService.createUser(createUserDto);
        ApiResponse<Object> apiResponse = ApiResponse.builder()
                .data(responseData)
                .message("User with email  "+ responseData.getEmail() +"  created successfully")
                .timestamp(LocalDateTime.now())
                .statusCode(HttpStatus.CREATED.value())
                .success(true)
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping
    public  ResponseEntity<?> getAllUsers(){
        List<User> responseData = userService.getAllUsers();
        ApiResponse<Object> apiResponse = ApiResponse.builder()
                .data(responseData)
                .message("List of registered users")
                .timestamp(LocalDateTime.now())
                .statusCode(HttpStatus.OK.value())
                .success(true)
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{userId}")
    public  ResponseEntity<?> getUserById(@PathVariable("userId") String userId){
        User responseData = userService.getUserById(userId);
        ApiResponse<Object> apiResponse = ApiResponse.builder()
                .data(responseData)
                .message("Requested user found")
                .timestamp(LocalDateTime.now())
                .statusCode(HttpStatus.OK.value())
                .success(true)
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<?> updateUser(@RequestBody @Valid CreateUserDto updateUser,@PathVariable String userId){
        User responseData = userService.updateUserById(updateUser,userId);
        ApiResponse<Object> apiResponse = ApiResponse.builder()
                .data(responseData)
                .message("Updated user")
                .timestamp(LocalDateTime.now())
                .statusCode(HttpStatus.OK.value())
                .success(true)
                .build();

        return ResponseEntity.ok(apiResponse);
    }



}
