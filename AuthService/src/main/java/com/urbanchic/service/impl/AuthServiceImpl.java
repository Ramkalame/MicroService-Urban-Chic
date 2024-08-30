package com.urbanchic.service.impl;


import com.urbanchic.client.SellerServiceClient;
import com.urbanchic.config.CustomUserDetails;
import com.urbanchic.dto.seller.SellerLoginRequestDto;
import com.urbanchic.dto.seller.SellerRegistrationDto;
import com.urbanchic.dto.UserDto;
import com.urbanchic.entity.User;
import com.urbanchic.even.SellerProfileCreatedEvent;
import com.urbanchic.exception.IncorrectPasswordException;
import com.urbanchic.external.SellerDto;
import com.urbanchic.service.AuthService;
import com.urbanchic.service.UserService;
import com.urbanchic.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final ApplicationEventPublisher eventPublisher;
    private final SellerServiceClient sellerServiceClient;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final UserService userService;



    @Override
    public User createSellerUser(SellerRegistrationDto sellerRegistrationDto) {
        UserDto newUser = UserDto.builder()
                .userName(sellerRegistrationDto.getSellerPrimaryEmail())
                .password(sellerRegistrationDto.getSellerPassword())
                .build();
        User savedUser = userService.createSellerUser(newUser);

        SellerDto newSellerDto = SellerDto.builder()
                .sellerId(savedUser.getId())
                .sellerFullName(sellerRegistrationDto.getSellerFullName())
                .sellerPrimaryEmail(sellerRegistrationDto.getSellerPrimaryEmail())
                .sellerPrimaryMoNumber(sellerRegistrationDto.getSellerPrimaryMoNumber())
                .build();
        eventPublisher.publishEvent(new SellerProfileCreatedEvent(this,newSellerDto));
        return savedUser;
    }


    @Async
    @EventListener
    private void sellerProfileCreatedEventListener(SellerProfileCreatedEvent sellerProfileCreatedEvent) {
        sellerServiceClient.createSeller(sellerProfileCreatedEvent.getSellerDto());
    }

    @Override
    public String sellerLogin(SellerLoginRequestDto sellerLoginRequestDto) {
        User existingUser = userService.getByUserName(sellerLoginRequestDto.getUserName());
        if (passwordEncoder.matches(sellerLoginRequestDto.getPassword(), existingUser.getPassword())){
                doAuthenticate(sellerLoginRequestDto.getUserName(),sellerLoginRequestDto.getPassword());
        }else{
            throw new IncorrectPasswordException("Incorrect Password");
        }
        CustomUserDetails userDetails = new CustomUserDetails(existingUser.getId(),existingUser.getUserName(),existingUser.getPassword(),existingUser.getRole());
        String jwtToken = jwtUtil.generateToken(userDetails);
        return jwtToken;
    }

    private void doAuthenticate(String userName,String password){
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userName,password);
            authenticationManager.authenticate(authenticationToken);
        }catch (BadCredentialsException e){
            throw new BadCredentialsException("Bad Credentials");
        }
    }
}
