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
@Document(collection = "seller_document_collection")
public class SellerDocument {

    @Id
    private String id;
    private String companyName;
    private String companyLogoUrl;
    private String gstNumber;
    private String panNumber;
    private String accountNumber;
    private String ifscCode;
    private String sellerId;
}
