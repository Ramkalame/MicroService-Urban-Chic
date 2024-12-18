package com.urbanchic.entity.helper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    private String id;
    private String houseNumber;                 // House or flat number
    private String streetName;                  // Street name
    private String landmark;                    // Nearby landmark
    private String city;                        // City or town
    private String district;                    // District
    private String state;                       // State
    private String country;
    private String pinCode;
    private String addressLabel;

}
