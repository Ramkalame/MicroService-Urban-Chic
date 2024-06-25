package com.urbanchic.service;

import com.urbanchic.dto.CreateUserDto;
import com.urbanchic.entity.User;

import java.util.List;
import java.util.Objects;

public interface UserService {

    CreateUserDto createUser(CreateUserDto createUserDto);

    List<User> getAllUsers();

    User getUserById(String userId);

    User updateUserById(CreateUserDto updateUser,String userId);


}
