package com.urbanchic.external;

import com.urbanchic.external.role.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String userId;
    private String fullName;
    private String email;
    private String mobileNo;
    private String profileImageUrl;
    private Role role;

}
