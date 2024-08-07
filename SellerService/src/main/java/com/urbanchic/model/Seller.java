package com.urbanchic.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "seller_collecion")
public class Seller {

    @Id
    private String sellerId;
    private String sellerFullName;
    private String sellerEmail;
    private String sellerPrimaryMoNumber;
    private String sellerPrimaryEmail;
    private String sellerSecondaryMoNumber;
    private String sellerSecondaryEmail;
    private String sellerAddress;
}
