package com.urbanchic.service.impl;


import com.urbanchic.client.SellerServiceClient;
import com.urbanchic.dto.SellerRegistrationDto;
import com.urbanchic.dto.UserDto;
import com.urbanchic.entity.User;
import com.urbanchic.even.SellerProfileCreatedEvent;
import com.urbanchic.external.SellerDto;
import com.urbanchic.service.AuthService;
import com.urbanchic.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final ApplicationEventPublisher eventPublisher;
    private final SellerServiceClient sellerServiceClient;
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

    @Override
    @Async
    @EventListener
    public void sellerProfileCreatedEventListner(SellerProfileCreatedEvent sellerProfileCreatedEvent) {
        sellerServiceClient.createSeller(sellerProfileCreatedEvent.getSellerDto());
    }


}
