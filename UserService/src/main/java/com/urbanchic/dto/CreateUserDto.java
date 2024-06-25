package com.urbanchic.dto;

import com.urbanchic.entity.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserDto {

    @NotBlank(message = "full name can not be null")
    private String fullName;

    @NotBlank(message = "email can not be null")
    @Email(message = "enter valid email")
    private String email;

    @NotBlank(message = "password can not be null")
    @Size(min = 6,max = 15, message = "password should must be between 6 and 15 characters")
    private String password;

    @NotBlank(message = "mobile no. can not be null")
    private String mobileNo;

    private String profileImageUrl;

    private Address address;


}
