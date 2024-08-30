package com.urbanchic.service.impl;

import com.urbanchic.dto.UserDto;
import com.urbanchic.entity.Role;
import com.urbanchic.entity.User;
import com.urbanchic.exception.EntityAlreadyExistsException;
import com.urbanchic.exception.EntityNotFoundException;
import com.urbanchic.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements com.urbanchic.service.UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User createSellerUser(UserDto userDto) {
        User existingUser = userRepository.findByUserName(userDto.getUserName()).orElse(null);
        if (existingUser != null){
            throw new EntityAlreadyExistsException("User name is already registered");
        }
        User newUser = User.builder()
                .userName(userDto.getUserName())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .role(Role.ROLE_SELLER.name())
                .build();
        User savedUser = userRepository.save(newUser);
        return savedUser;
    }

    @Override
    public User getByUserName(String userName) {
        User existingUser = userRepository.findByUserName(userName).orElseThrow(() ->
                new EntityNotFoundException("User Not Found"));
        return existingUser;
    }


}
