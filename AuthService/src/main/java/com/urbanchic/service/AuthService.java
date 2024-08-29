package com.urbanchic.service;

import com.urbanchic.dto.SellerRegistrationDto;
import com.urbanchic.entity.User;
import com.urbanchic.even.SellerProfileCreatedEvent;


public interface AuthService {

        User createSellerUser(SellerRegistrationDto sellerRegistrationDto);
        void sellerProfileCreatedEventListner(SellerProfileCreatedEvent sellerProfileCreatedEvent);

}
