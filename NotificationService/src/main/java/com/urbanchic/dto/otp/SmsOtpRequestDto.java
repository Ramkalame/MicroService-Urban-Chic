package com.urbanchic.dto.otp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SmsOtpRequestDto {

    private String userName;
    private String phoneNumber;

}
