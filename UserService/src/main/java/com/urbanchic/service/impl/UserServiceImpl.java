package com.urbanchic.service.impl;

import com.urbanchic.dto.CreateUserDto;
import com.urbanchic.entity.Address;
import com.urbanchic.entity.User;
import com.urbanchic.exception.UserAlreadyExistsException;
import com.urbanchic.repository.AddressRepository;
import com.urbanchic.repository.UserRepository;
import com.urbanchic.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final AddressRepository addressRepository;


    /**
     * Creates a new user with the given details.
     *
     * @param createUserDto the data transfer object containing the user details
     * @return a message indicating the result of the user creation process:
     *         - "User already exists." if a user with the given email already exists
     *         - "User with email {email} created successfully" if the user was created successfully
     */
    @Override
    public String createUser(CreateUserDto createUserDto) {
        User existingUser = userRepository.findUserByEmail(createUserDto.getEmail()).orElse(null);
        if (existingUser != null) {
            throw  new UserAlreadyExistsException("User with email "+createUserDto.getEmail()+" is already exists.");
        }
        User newUser = User.builder()
                .fullName(createUserDto.getFullName())
                .email(createUserDto.getEmail())
                .password(createUserDto.getPassword())
                .mobileNo(createUserDto.getMobileNo())
                .profileImageUrl(createUserDto.getProfileImageUrl())
                .build();
        User savedUser = userRepository.save(newUser);

        Address newAddress = Address.builder()
                .street(createUserDto.getAddress().getStreet())
                .city(createUserDto.getAddress().getCity())
                .state(createUserDto.getAddress().getState())
                .zipCode(createUserDto.getAddress().getZipCode())
                .userId(savedUser.getUserId())
                .build();
        addressRepository.save(newAddress);
        return "User with email " + savedUser.getEmail() + " created successfully";
    }

    @Override
    public List<User> getAllUsers() {
        return List.of();
    }

    /**
     * 
     * @param userId
     * @return
     */
    @Override
    public User getUserById(String userId) {
        return null;
    }

    @Override
    public User updateUserById(CreateUserDto updateUser) {
        return null;
    }
}
