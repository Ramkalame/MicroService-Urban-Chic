package com.urbanchic.service.impl;

import com.urbanchic.dto.CreateUserDto;
import com.urbanchic.entity.Address;
import com.urbanchic.entity.User;
import com.urbanchic.exception.AddressNotFoundException;
import com.urbanchic.exception.UserAlreadyExistsException;
import com.urbanchic.exception.UserNotFoundException;
import com.urbanchic.repository.AddressRepository;
import com.urbanchic.repository.UserRepository;
import com.urbanchic.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

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
     * - "User already exists." if a user with the given email already exists
     * - "User with email {email} created successfully" if the user was created successfully
     */
    @Override
    public CreateUserDto createUser(CreateUserDto createUserDto) {
        User existingUser = userRepository.findUserByEmail(createUserDto.getEmail()).orElse(null);
        if (existingUser != null) {
            throw new UserAlreadyExistsException("User with email " + createUserDto.getEmail() + " is already exists.");
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
        Address savedAddress = addressRepository.save(newAddress);

        CreateUserDto createUserDtoResponse = CreateUserDto.builder()
                .fullName(savedUser.getFullName())
                .email(savedUser.getEmail())
                .password(savedUser.getPassword())
                .mobileNo(savedUser.getMobileNo())
                .profileImageUrl(savedUser.getProfileImageUrl())
                .address(savedAddress)
                .build();

        return createUserDtoResponse;
    }

    /**
     * Retrieves all users from the user repository.
     *
     * @return A list of User objects representing all users in the repository.
     * If no users exist, an empty list is returned.
     */
    @Override
    public List<User> getAllUsers() {
        List<User> userList = userRepository.findAll();
        if (userList.isEmpty()) {
            throw new NoSuchElementException("There is no registered user");
        }
        return userList;
    }

    /**
     * Retrieves a user from the repository based on their unique identifier.
     *
     * @param userId The unique identifier of the user to retrieve.
     * @return The User object representing the requested user, or throws an exception if the user is not found.
     * @throws UserNotFoundException If a user with the provided `userId` cannot be found in the repository.
     */
    @Override
    public User getUserById(String userId) {
        User existingUser = userRepository.findById(userId).orElseThrow(() ->
                new UserNotFoundException("Requested user does not exist."));
        return existingUser;
    }

    @Override
    public User updateUserById(CreateUserDto updateUser, String userId) {
        User existingUser = userRepository.findById(userId).orElseThrow(() ->
                new UserNotFoundException("Requested user does not exist."));
        existingUser.setFullName(updateUser.getFullName());
        existingUser.setEmail(updateUser.getEmail());
        existingUser.setPassword(updateUser.getPassword());
        existingUser.setMobileNo(updateUser.getMobileNo());
        existingUser.setProfileImageUrl(updateUser.getProfileImageUrl());

        return userRepository.save(existingUser);
    }
}
