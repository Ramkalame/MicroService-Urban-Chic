package com.urbanchic.config;

import com.urbanchic.entity.Otp;
import com.urbanchic.repository.OtpRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final OtpRepository otpRepository;

    @Override
    public UserDetails loadUserByUsername(String mobileNo) throws UsernameNotFoundException {
//        User user = userServiceClient.getUserByMobileNo(mobileNo).getData();
//        Otp otp = otpRepository.findByMoNumber(mobileNo).get();
        
//        return  new CustomUserDetails(user.getMobileNo(),otp.getOtpNumber(),user.getRole());
        return  null;
    }
}
