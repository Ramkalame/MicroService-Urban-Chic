package com.urbanchic.entity;


import com.urbanchic.entity.sellerEnum.SellerStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "seller_document_collecion")
public class SellerDocument {

    @Id
    private String sellerDocumentId;
    private String aadhaarImg  ;
    private String aadhaarNo;
    private String panImg;
    private String panNo;
    private String bankFrontPageImg;
    private String accountNo;
    private String ifscCode;

    @Field(targetType = FieldType.STRING)
    private SellerStatus sellerStatus;

    private String sellerId;
}
