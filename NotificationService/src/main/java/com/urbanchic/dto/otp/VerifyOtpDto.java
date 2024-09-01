package com.urbanchic.dto.otp;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VerifyOtpDto {

    @NotBlank(message = "Email Or Number is required")
    private String emailOrNumber;
    @NotBlank(message = "Otp is required")
    private String otpNumber;

}
