package com.urbanchic.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserSocialRegistrationDto {

    private String fullName;

    private String email;

    private String mobileNo;

    private  String profileImageUrl;

}
