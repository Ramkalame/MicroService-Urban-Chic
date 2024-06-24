package com.urbanchic.entity;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "user_collection")
public class User {

    @Id
    @NotBlank(message = "user id can not be null")
    private String userId;

    @NotBlank(message = "full name can not be null")
    private  String fullName;

    @NotBlank(message = "email can not be null")
    @Email(message = "enter valid email")
    private String email;

    @NotBlank(message = "password can not be null")
    @Size(min = 6,max = 15, message = "password should must be between 6 and 15 characters")
    private  String password;

    @NotBlank(message = "mobile no. can not be null")
    private  String mobileNo;

    private  String profileImageUrl;

}
