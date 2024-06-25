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
    @NotBlank(message = "address id can not be null")
    private  String addressId;

    @NotBlank(message = "street can not be null")
    private  String street;

    @NotBlank(message = "city can not be null")
    private  String city;

    @NotBlank(message = "state can not be null")
    private  String state;

    @NotBlank(message = "zip code can not be null")
    private  String zipCode;

    private  String userId;
}
