package com.urbanchic.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OtpDto {

    @NotBlank(message = "email or phone number is required")
    private String emailOrNumber;
    @NotBlank(message = "otp is required")
    private String otpNumber;

}
