package com.urbanchic.external;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BuyerDto {

    private String buyerId;
    private String name;
    private String gender;
    private String email;
    private String password;
    private String phoneNumber;

}
