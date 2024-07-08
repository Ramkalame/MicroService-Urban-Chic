package com.urbanchic.service.impl;

import com.urbanchic.client.UserServiceClient;
import com.urbanchic.dto.LoginRequestDto;
import com.urbanchic.dto.OtpVerificationDto;
import com.urbanchic.dto.UserRegistrationDto;
import com.urbanchic.dto.UserSocialRegistrationDto;
import com.urbanchic.external.User;
import com.urbanchic.service.AuthService;
import com.urbanchic.service.OtpService;
import com.urbanchic.service.TokenService;
import com.urbanchic.util.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserServiceClient userServiceClient;
    private final TokenService tokenService;
    private final OtpService otpService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;


    @Override
    public ApiResponse<User> buyerRegister(UserRegistrationDto userRegistrationDto) {
        ApiResponse<User> responseData = userServiceClient.register(userRegistrationDto);
        return responseData;
    }

    @Override
    public ApiResponse<User> buyerSocialRegister(UserSocialRegistrationDto userSocialRegistrationDto) {
        ApiResponse<User> responseData = userServiceClient.registerSocial(userSocialRegistrationDto);
        return responseData;
    }

    @Override
    public String login(LoginRequestDto loginRequestDto) {
        String jwtToken = null;
        User existingUser = userServiceClient.getUserByMobileNo(loginRequestDto.getMobileNo()).getData();
        OtpVerificationDto otpVerificationDto = OtpVerificationDto.builder()
                .moNumber(loginRequestDto.getMobileNo())
                .otpNumber(loginRequestDto.getOtp())
                .build();

        if (otpService.verifyOtp(otpVerificationDto)){
            Authentication authentication = new
                    UsernamePasswordAuthenticationToken(loginRequestDto.getMobileNo(),loginRequestDto.getOtp());
            authenticationManager.authenticate(authentication);
            UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequestDto.getMobileNo());
            jwtToken =  tokenService.generateJwtToken(userDetails);
        }
        return jwtToken;
    }
}
