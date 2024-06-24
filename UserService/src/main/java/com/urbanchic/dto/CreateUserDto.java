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

    private String fullName;

    private String email;

    private String password;

    private String mobileNo;

    private String profileImageUrl;

    private Address address;


}
