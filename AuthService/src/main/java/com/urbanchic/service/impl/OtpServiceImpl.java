package com.urbanchic.service.impl;

import com.urbanchic.client.NotificationServiceClient;
import com.urbanchic.dto.OtpRequestDto;
import com.urbanchic.dto.OtpSmsResponseDto;
import com.urbanchic.dto.OtpVerificationDto;
import com.urbanchic.entity.Otp;
import com.urbanchic.exception.OtpExpiredException;
import com.urbanchic.exception.EntityNotFoundException;
import com.urbanchic.repository.OtpRepository;
import com.urbanchic.service.OtpService;
import com.urbanchic.util.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OtpServiceImpl implements OtpService {

    private final OtpRepository otpRepository;
    private final NotificationServiceClient notificationServiceClient;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Otp getOtpByOtpNumber(String otpNumber) {
        Otp existingOtp = otpRepository.findByOtpNumber(otpNumber).orElseThrow(() ->
                new EntityNotFoundException("Otp not found"));
        return existingOtp;
    }

    @Override
    public boolean verifyOtp(OtpVerificationDto otpVerificationDto) {
        Otp existingOtp = otpRepository.findByMoNumber(otpVerificationDto.getMoNumber()).orElseThrow(() ->
                new EntityNotFoundException("INVALID OTP:not found"));

        LocalDateTime nowDateTime = LocalDateTime.now();
        LocalDateTime expirationTime = existingOtp.getOtpCreationTime().plusSeconds(60);
        if (!passwordEncoder.matches(otpVerificationDto.getOtpNumber(),existingOtp.getOtpNumber())){
            return  false;
        }
        if (nowDateTime.isAfter(expirationTime)) {
            throw new OtpExpiredException("Otp is expired ");
        }
        return true;
    }

    @Override
    public Otp sendOtp(OtpRequestDto otpRequestDto) {
        ApiResponse<OtpSmsResponseDto> message = notificationServiceClient.sendOtp(otpRequestDto);
        Otp existingOtp = otpRepository.findByMoNumber(otpRequestDto.getPhoneNumber()).orElse(null);
        if (existingOtp == null) {
            Otp otp = Otp.builder()
                    .otpNumber(passwordEncoder.encode(message.getData().getOtp()))
                    .moNumber(message.getData().getTo())
                    .otpCreationTime(LocalDateTime.now())
                    .build();
            otpRepository.save(otp);
            return otp;
        }
        existingOtp.setOtpNumber(passwordEncoder.encode(message.getData().getOtp()));
        existingOtp.setOtpCreationTime(LocalDateTime.now());
        return otpRepository.save(existingOtp);
    }

}
