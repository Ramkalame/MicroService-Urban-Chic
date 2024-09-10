package com.urbanchic.service;

import com.urbanchic.dto.LoginResponseDto;
import com.urbanchic.dto.seller.SellerLoginRequestDto;
import com.urbanchic.dto.seller.SellerRegistrationDto;
import com.urbanchic.entity.User;


public interface AuthService {

        User createSellerUser(SellerRegistrationDto sellerRegistrationDto);
        LoginResponseDto sellerLogin(SellerLoginRequestDto sellerLoginRequestDto);
        User updateSellerAccountStatus(String sellerId);

}
