package com.urbanchic.client;

import com.urbanchic.dto.UserRegistrationDto;
import com.urbanchic.dto.UserSocialRegistrationDto;
import com.urbanchic.external.User;
import com.urbanchic.util.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "USERSERVICE")
public interface UserServiceClient {

    @PostMapping("/users/create")
    ApiResponse<User> register(@RequestBody UserRegistrationDto userRegistrationDto);

    @PostMapping("/users/create/social")
    ApiResponse<User> registerSocial(@RequestBody UserSocialRegistrationDto userSocialRegistrationDto);

    @GetMapping("/users/email/{email}")
    ApiResponse<User> getUserByEmail(@PathVariable("email") String email);

    @GetMapping("/users/mobileNo/{mobileNo}")
    ApiResponse<User> getUserByMobileNo(@PathVariable("mobileNo") String mobileNo);


}
