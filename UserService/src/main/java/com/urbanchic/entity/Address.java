package com.urbanchic.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
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
@Document(collection = "address_collection")
public class Address {

    @Id
    private  String addressId;
    private  String street;
    private  String city;
    private  String state;
    private  String zipCode;
    private  String userId;
}
