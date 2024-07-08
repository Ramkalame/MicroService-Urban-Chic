package com.urbanchic.service.impl;

import com.urbanchic.dto.CreateSocialUserDto;
import com.urbanchic.dto.CreateUserDto;
import com.urbanchic.entity.User;
import com.urbanchic.entity.role.Role;
import com.urbanchic.exception.UserAlreadyExistsException;
import com.urbanchic.exception.UserNotFoundException;
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

    /**
     * Creates a new user with the given details.
     *
     * @param createUserDto the data transfer object containing the user details
     * @return a message indicating the result of the user creation process:
     * - "User already exists." if a user with the given email already exists
     * - "User with email {email} created successfully" if the user was created successfully
     */
    @Override
    public User createUser(CreateUserDto createUserDto) {
        User existingUserWithEmail = userRepository.findUserByEmail(createUserDto.getEmail()).orElse(null);
        if (existingUserWithEmail != null) {
            throw new UserAlreadyExistsException("Email " + createUserDto.getEmail() + " is already registered.");
        }
        User existingUserWithMobileNo = userRepository.findByMobileNo(createUserDto.getMobileNo()).orElse(null);
        if (existingUserWithMobileNo != null) {
            throw new UserAlreadyExistsException("Mobile Number " + createUserDto.getMobileNo() + " is already registered.");
        }
        User newUser = User.builder()
                .fullName(createUserDto.getFullName())
                .email(createUserDto.getEmail())
                .mobileNo(createUserDto.getMobileNo())
                .profileImageUrl(null)
                .role(Role.BUYER)
                .build();
        User savedUser = userRepository.save(newUser);

        return savedUser;
    }

    @Override
    public User createSocailUser(CreateSocialUserDto createSocialUserDto) {
        User existingUser = userRepository.findUserByEmail(createSocialUserDto.getEmail()).orElse(null);
        if (existingUser != null) {
            throw new UserAlreadyExistsException("User with email " + createSocialUserDto.getEmail() + " is already exists.");
        }
        User newUser = User.builder()
                .fullName(createSocialUserDto.getFullName())
                .email(createSocialUserDto.getEmail())
                .mobileNo(createSocialUserDto.getMobileNo())
                .profileImageUrl(createSocialUserDto.getProfileImageUrl())
                .role(Role.BUYER)
                .build();
        User savedUser = userRepository.save(newUser);

        return savedUser;
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
                new UserNotFoundException("Requested user does not exist ."));
        return existingUser;
    }

    @Override
    public User updateUserById(CreateUserDto updateUser, String userId) {
        User existingUser = userRepository.findById(userId).orElseThrow(() ->
                new UserNotFoundException("Requested user does not exist."));
        existingUser.setFullName(updateUser.getFullName());
        existingUser.setEmail(updateUser.getEmail());
        existingUser.setMobileNo(updateUser.getMobileNo());

        return userRepository.save(existingUser);
    }

    @Override
    public User getUserByEmail(String email) {
        User existingUser = userRepository.findUserByEmail(email).orElseThrow(() ->
                new UserNotFoundException("Incorrect email"));
        return existingUser;
    }

    @Override
    public User getUserByMobileNo(String mobileNo) {
        User existingUser = userRepository.findByMobileNo(mobileNo).orElseThrow(() ->
                new UserNotFoundException("Incorrect number"));
        return existingUser;
    }
}
