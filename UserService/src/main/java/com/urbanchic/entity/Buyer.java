package com.urbanchic.entity;

import com.urbanchic.entity.helper.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "buyer_details_collection")
public class Buyer {

    @Id
    private String id;
    private String buyerId;
    private String name;
    private String gender;
    private String email;
    private String phoneNumber;
    private String role;
    private List<Address> addressList;
    private boolean isEmailVerified;
    private boolean isPhoneNumberVerified;

}
