package com.urbanchic.dto;

import com.urbanchic.external.Product;
import com.urbanchic.external.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SmsAndEmailDetailsDto {

    private User user;
    private List<Product> products;

}
