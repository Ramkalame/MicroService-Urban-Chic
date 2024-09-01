package com.urbanchic.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "otp_collections")
public class Otp {
    @Id
    private String id;
    private String emailOrNumber;
    private String otpNumber;
    private LocalDateTime createdDate;
    private LocalDateTime expiryDate;
}
