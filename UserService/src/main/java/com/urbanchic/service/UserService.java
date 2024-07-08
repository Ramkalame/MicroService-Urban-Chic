package com.urbanchic.service;

import com.urbanchic.dto.CreateSocialUserDto;
import com.urbanchic.dto.CreateUserDto;
import com.urbanchic.entity.User;

import java.util.List;
import java.util.Objects;

public interface UserService {

    User createUser(CreateUserDto createUserDto);
    User createSocailUser(CreateSocialUserDto createSocialUserDto);
    List<User> getAllUsers();
    User getUserById(String userId);
    User updateUserById(CreateUserDto updateUser,String userId);
    User getUserByEmail(String email);
    User getUserByMobileNo(String mobileNo);


}
