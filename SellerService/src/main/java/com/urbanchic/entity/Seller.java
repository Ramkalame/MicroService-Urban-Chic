package com.urbanchic.entity;


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
@Document(collection = "seller_details_collection")
public class Seller {

    @Id
    private String Id;
    private String sellerId;
    private String sellerFullName;
    private String sellerPrimaryMoNumber;
    private String sellerPrimaryEmail;
}
