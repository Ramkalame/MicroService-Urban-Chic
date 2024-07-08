package com.urbanchic.client;

import com.urbanchic.dto.OtpRequestDto;
import com.urbanchic.dto.OtpSmsResponseDto;
import com.urbanchic.util.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "NOTIFICATIONSERVICE")
public interface NotificationServiceClient {

    @PostMapping("/sms/send/otp")
    ApiResponse<OtpSmsResponseDto> sendOtp(@RequestBody OtpRequestDto otpRequestDto);


}
