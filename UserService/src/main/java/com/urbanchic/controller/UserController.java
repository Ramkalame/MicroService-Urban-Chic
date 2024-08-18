package com.urbanchic.controller;

import com.urbanchic.dto.CreateSocialUserDto;
import com.urbanchic.dto.CreateUserDto;
import com.urbanchic.entity.User;
import com.urbanchic.service.UserService;
import com.urbanchic.util.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<User>> createUser(@RequestBody @Valid CreateUserDto createUserDto) {
        User responseData = userService.createUser(createUserDto);
        ApiResponse<User> apiResponse = ApiResponse.<User>builder()
                .data(responseData)
                .message("User with email  " + responseData.getEmail() + "  created successfully")
                .timestamp(LocalDateTime.now())
                .statusCode(HttpStatus.CREATED.value())
                .success(true)
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/create/social")
    public ResponseEntity<ApiResponse<User>> createSocialUser(@RequestBody @Valid CreateSocialUserDto createSocialUserDto) {
        User responseData = userService.createSocailUser(createSocialUserDto);
        ApiResponse<User> apiResponse = ApiResponse.<User>builder()
                .data(responseData)
                .message("User with email  " + responseData.getEmail() + "  created successfully")
                .timestamp(LocalDateTime.now())
                .statusCode(HttpStatus.CREATED.value())
                .success(true)
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
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

    @GetMapping("/userid/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable("userId") String userId) {
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

    @GetMapping("/email/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable("email") String email) {
        User responseData = userService.getUserByEmail(email);
        ApiResponse<Object> apiResponse = ApiResponse.builder()
                .data(responseData)
                .message("Requested user found")
                .timestamp(LocalDateTime.now())
                .statusCode(HttpStatus.OK.value())
                .success(true)
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/mobileNo/{mobileNo}")
    public ResponseEntity<?> getUserByPhoneNumber(@PathVariable("mobileNo") String mobileNo) {
        User responseData = userService.getUserByMobileNo(mobileNo);
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
    public ResponseEntity<?> updateUser(@RequestBody @Valid CreateUserDto updateUser, @PathVariable String userId) {
        User responseData = userService.updateUserById(updateUser, userId);
        ApiResponse<Object> apiResponse = ApiResponse.builder()
                .data(responseData)
                .message("Updated user")
                .timestamp(LocalDateTime.now())
                .statusCode(HttpStatus.OK.value())
                .success(true)
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/hello")
    public String hello(){
        return "Working !!!!!!!!!!!!!";
    }


}
