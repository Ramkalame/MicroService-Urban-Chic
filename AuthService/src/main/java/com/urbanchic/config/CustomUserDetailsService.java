package com.urbanchic.config;

import com.urbanchic.entity.User;
import com.urbanchic.exception.EntityNotFoundException;
import com.urbanchic.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(userName).orElseThrow(() ->
                new EntityNotFoundException("User Not Found"));
        return  new CustomUserDetails(user.getId(),user.getUserName(),user.getPassword(), user.getRole());
    }
}
