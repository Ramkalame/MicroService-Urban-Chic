package com.urbanchic.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OtpSmsResponseDto {

    private String otp;
    private String from;
    private String to;
    private String message;
}
