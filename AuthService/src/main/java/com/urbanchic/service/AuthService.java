package com.urbanchic.service;

import com.urbanchic.dto.LoginRequestDto;
import com.urbanchic.dto.UserRegistrationDto;
import com.urbanchic.dto.UserSocialRegistrationDto;
import com.urbanchic.external.User;
import com.urbanchic.util.ApiResponse;


public interface AuthService {

    ApiResponse<User> buyerRegister(UserRegistrationDto userRegistrationDto);
    ApiResponse<User> buyerSocialRegister(UserSocialRegistrationDto userSocialRegistrationDto);
    String login(LoginRequestDto loginRequestDto);

}
