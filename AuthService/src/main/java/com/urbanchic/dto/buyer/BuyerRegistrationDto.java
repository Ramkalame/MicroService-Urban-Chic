package com.urbanchic.dto.buyer;

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
public class BuyerRegistrationDto {

//    @NotBlank(message = "first name is required")
//    private String firstName;
//    @NotBlank(message = "last name is required")
//    private String lastName;
//    @Email
//    @NotBlank(message = "email is required")
//    private String email;
//    @NotBlank(message = "phone number is required")
    @Size(min = 10,max = 10)
    private String phoneNumber;

}
