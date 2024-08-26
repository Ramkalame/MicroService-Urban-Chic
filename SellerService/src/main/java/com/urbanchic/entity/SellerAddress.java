package com.urbanchic.entity;


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
@Document(collection = "seller_address_collection")
public class SellerAddress {

    @Id
    private String id;
    private String street;
    private String city;
    private String state;
    private String country;
    private String postalCode;
    private String sellerId;

}
