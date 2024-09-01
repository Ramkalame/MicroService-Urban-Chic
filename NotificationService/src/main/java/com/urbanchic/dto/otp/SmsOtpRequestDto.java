package com.urbanchic.dto.otp;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SmsOtpRequestDto {

    @NotBlank(message = "User Name is required")
    private String userName;
    @NotBlank(message = "Phone Number is required")
    private String phoneNumber;

}
