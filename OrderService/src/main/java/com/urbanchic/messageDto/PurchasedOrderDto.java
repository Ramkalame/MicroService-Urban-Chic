package com.urbanchic.messageDto;

import com.urbanchic.external.Product;
import lombok.*;

import java.util.List;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PurchasedOrderDto {

    private String orderId;
    private String buyerName;
    private String email;
    private String mobileNumber;
    private List<Product> orderedProductList;
    private Integer beforeTaxAmount;
    private Double estimatedTax;

}
