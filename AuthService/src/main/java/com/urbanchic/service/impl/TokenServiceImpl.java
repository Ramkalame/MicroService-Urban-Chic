package com.urbanchic.service.impl;

import com.urbanchic.service.TokenService;
import com.urbanchic.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

    private final JwtUtil jwtUtil;

    @Override
    public String generateJwtToken(UserDetails userDetails) {
        String jwtToken = jwtUtil.generateToken(userDetails);
        return jwtToken;
    }

    @Override
    public Boolean validateJwtToken(String jwtToken,UserDetails userDetails) {
        Boolean isTokenValid = jwtUtil.validateToken(jwtToken,userDetails);
        return isTokenValid;
    }
}
