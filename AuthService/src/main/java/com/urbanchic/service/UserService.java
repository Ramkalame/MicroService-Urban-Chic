package com.urbanchic.service;

import com.urbanchic.dto.UserDto;
import com.urbanchic.entity.User;

public interface UserService {

    User createSellerUser(UserDto userDto);
    User getByUserName(String userName);
    User updateSellerAccountStatus(String sellerId);
    User createBuyerUser(UserDto userDto);
}
