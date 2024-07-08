package com.urbanchic.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface TokenService {

   String generateJwtToken(UserDetails userDetails);
   Boolean validateJwtToken(String jwtToken,UserDetails userDetails);
}
