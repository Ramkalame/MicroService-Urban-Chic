package com.urbanchic.service;

import com.urbanchic.dto.UserDto;
import com.urbanchic.entity.User;

public interface UserService {

    User createSellerUser(UserDto userDto);
}
