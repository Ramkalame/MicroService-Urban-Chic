package com.urbanchic.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateSocialUserDto {

    @NotBlank(message = "full name can not be null")
    private String fullName;

    @NotBlank(message = "email can not be null")
    @Email(message = "enter valid email")
    private String email;

    @NotBlank(message = "mobile no. can not be null")
    private String mobileNo;

    private  String profileImageUrl;

}
