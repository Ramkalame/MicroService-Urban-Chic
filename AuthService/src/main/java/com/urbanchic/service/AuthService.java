package com.urbanchic.service;

import com.urbanchic.dto.seller.SellerLoginRequestDto;
import com.urbanchic.dto.seller.SellerRegistrationDto;
import com.urbanchic.entity.User;
import com.urbanchic.even.SellerProfileCreatedEvent;


public interface AuthService {

        User createSellerUser(SellerRegistrationDto sellerRegistrationDto);
        String sellerLogin(SellerLoginRequestDto sellerLoginRequestDto);

}
