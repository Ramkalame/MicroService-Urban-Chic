package com.urbanchic.service.impl;


import com.urbanchic.config.CustomUserDetails;
import com.urbanchic.config.PhoneNumberAuthenticationToken;
import com.urbanchic.dto.LoginResponseDto;
import com.urbanchic.dto.buyer.BuyerLoginDto;
import com.urbanchic.dto.buyer.BuyerRegistrationDto;
import com.urbanchic.dto.seller.SellerLoginRequestDto;
import com.urbanchic.dto.seller.SellerRegistrationDto;
import com.urbanchic.dto.UserDto;
import com.urbanchic.entity.User;
import com.urbanchic.entity.sellerEnum.SellerStatus;
import com.urbanchic.event.SellerProfileCreatedEvent;
import com.urbanchic.exception.IncorrectPasswordException;
import com.urbanchic.external.SellerDto;
import com.urbanchic.service.AuthService;
import com.urbanchic.service.UserService;
import com.urbanchic.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final ApplicationEventPublisher eventPublisher;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final UserService userService;



    @Override
    public User createSellerUser(SellerRegistrationDto sellerRegistrationDto) {
        UserDto newUser = UserDto.builder()
                .userName(sellerRegistrationDto.getSellerPrimaryEmail())
                .password(sellerRegistrationDto.getSellerPassword())
                .userAccountStatus(SellerStatus.CONTACT_DETAILS_VERIFIED.name())
                .build();
        User savedUser = userService.createSellerUser(newUser);
        log.info("user is registered");
        SellerDto newSellerDto = SellerDto.builder()
                .sellerId(savedUser.getId())
                .sellerFullName(sellerRegistrationDto.getSellerFullName())
                .sellerPrimaryEmail(sellerRegistrationDto.getSellerPrimaryEmail())
                .sellerPrimaryMoNumber(sellerRegistrationDto.getSellerPrimaryMoNumber())
                .build();
        eventPublisher.publishEvent(new SellerProfileCreatedEvent(this,newSellerDto));
        log.info("event is published");
        return savedUser;
    }



    //Service Method implementation for seller login
    @Override
    public LoginResponseDto sellerLogin(SellerLoginRequestDto sellerLoginRequestDto) {
        /**
        >> try to get the user from db by provided username by the user.
        >> if user with given username exists in db then continue.
        >> if not then the service method will throw incorrect username exception
        */
        User existingUser = userService.getByUserName(sellerLoginRequestDto.getUserName());

        /**
        * >> If user is found then try to match the given raw password with extracted decoded password
        *    from the found user.
        * >> If password does not match throw Incorrect Password Exception.
        * >> If matched try to authenticate the user by UsernamePasswordAuthenticationToken
        * */
        if (passwordEncoder.matches(sellerLoginRequestDto.getPassword(), existingUser.getPassword())){
                doAuthenticateSeller(sellerLoginRequestDto.getUserName(),sellerLoginRequestDto.getPassword());
        }else{
            throw new IncorrectPasswordException("Incorrect Password");
        }
        CustomUserDetails userDetails = new CustomUserDetails(
                existingUser.getId(),
                existingUser.getUserName(),
                existingUser.getRole());
        String jwtToken = jwtUtil.generateToken(userDetails);

        LoginResponseDto loginResponseDto = LoginResponseDto.builder()
                .jwtToken(jwtToken)
                .userAccountStatus(existingUser.getUserAccountStatus())
                .build();
        return loginResponseDto;
    }

    /**
     * @param userName first argument as username
     * @param password second argument as password
     * >> This takes two parameter and inside try block it creates {@link UsernamePasswordAuthenticationToken}
     *                 and then it calls the authenticate method of authenticationManager which uses the loadByUsername
     *                 method of {@link org.springframework.security.core.userdetails.UserDetailsService} class,
     *                 which then checks the username and password from the db.
     *
     * */
    private void doAuthenticateSeller(String userName,String password){
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userName,password);
            authenticationManager.authenticate(authenticationToken);
        }catch (BadCredentialsException e){
            throw new BadCredentialsException("Bad Credentials");
        }
    }

    private void doAuthenticateBuyer(String phoneNumber){
        try {
            PhoneNumberAuthenticationToken authenticationToken = new PhoneNumberAuthenticationToken(phoneNumber,null);
            authenticationManager.authenticate(authenticationToken);
        }catch (BadCredentialsException e){
            throw new BadCredentialsException("Bad Credentials");
        }
    }


    

    @Override
    public User updateSellerAccountStatus(String sellerId) {
        return userService.updateSellerAccountStatus(sellerId);
    }

    @Override
    public User createBuyerUser(BuyerRegistrationDto buyerRegistrationDto) {
        log.info(">>>>>>>>>>>>> {}",buyerRegistrationDto.getPhoneNumber());
        UserDto newUserDto = UserDto.builder()
                .userName(buyerRegistrationDto.getPhoneNumber())
                .build();
        User savedUser = userService.createBuyerUser(newUserDto);
//        BuyerDto newBuyerDto = BuyerDto.builder()
//                .buyerId(savedUser.getId())
//                .firstName(buyerRegistrationDto.getFirstName())
//                .lastName(buyerRegistrationDto.getLastName())
//                .email(buyerRegistrationDto.getEmail())
//                .phoneNumber(savedUser.getUserName())
//                .build();
        return savedUser;
    }

    @Override
    public LoginResponseDto buyerLogin(BuyerLoginDto buyerLoginDto) {
        User existingUser = userService.getByUserName(buyerLoginDto.getPhoneNumber());
        doAuthenticateBuyer(existingUser.getUserName());
        CustomUserDetails userDetails = new CustomUserDetails(
                existingUser.getId(),
                existingUser.getUserName(),
                existingUser.getRole());
        String jwtToken = jwtUtil.generateToken(userDetails);

        LoginResponseDto loginResponseDto = LoginResponseDto.builder()
                .jwtToken(jwtToken)
                .build();
        return loginResponseDto;
    }


}
