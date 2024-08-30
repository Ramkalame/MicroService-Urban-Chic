package com.urbanchic.service.impl;

import com.urbanchic.client.ProductServiceClient;
import com.urbanchic.client.UserServiceClient;
import com.urbanchic.service.SmsAndEmailDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SmsAndEmailDetailsServiceImpl implements SmsAndEmailDetailsService {

//    private final ProductServiceClient productServiceClient;
//    private final UserServiceClient userServiceClient;

    @Override
    public void prepareSmsAndEmailDetails(String orderId) {


    }
}
