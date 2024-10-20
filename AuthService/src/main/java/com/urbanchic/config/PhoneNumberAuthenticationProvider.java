package com.urbanchic.config;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class PhoneNumberAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsService userDetailsService;

    public PhoneNumberAuthenticationProvider(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String phoneNumber = (String) authentication.getPrincipal(); // The phone number (principal)
        // Load user details using the phone number
        UserDetails userDetails = userDetailsService.loadUserByUsername(phoneNumber); // UserDetailsService loads by username, which in this case is phone number
        // Create a new authenticated token with authorities
        return new PhoneNumberAuthenticationToken(phoneNumber, userDetails.getAuthorities());

    }


    @Override
    public boolean supports(Class<?> authentication) {
        // This ensures that this provider is used for PhoneNumberAuthenticationToken
        return PhoneNumberAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
