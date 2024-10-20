package com.urbanchic.service.impl;

import com.urbanchic.dto.UserDto;
import com.urbanchic.entity.Role;
import com.urbanchic.entity.User;
import com.urbanchic.entity.sellerEnum.SellerStatus;
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
                .userAccountStatus(userDto.getUserAccountStatus())
                .build();
        User savedUser = userRepository.save(newUser);
        return savedUser;
    }

    @Override
    public User getByUserName(String userName) {
        User existingUser = userRepository.findByUserName(userName).orElseThrow(() ->
                new EntityNotFoundException("Incorrect Username"));
        return existingUser;
    }

    @Override
    public User updateSellerAccountStatus(String sellerId) {
        User existingUser = userRepository.findById(sellerId).orElseThrow(() ->
                new EntityNotFoundException("user not found"));
        existingUser.setUserAccountStatus(SellerStatus.DOCUMENTS_VERIFIED.name());
       return userRepository.save(existingUser);
    }

    @Override
    public User createBuyerUser(UserDto userDto) {
        User existingUser = userRepository.findByUserName(userDto.getUserName()).orElse(null);
        if (existingUser != null){
            throw new EntityAlreadyExistsException("Phone Number is already registered");
        }
        User newUser = User.builder()
                .userName(userDto.getUserName())
                .phoneNumber(userDto.getUserName())
                .role(Role.ROLE_BUYER.name())
                .build();
        User savedUser = userRepository.save(newUser);
        return savedUser;
    }


}
