package com.urbanchic.external;

import lombok.*;

import java.util.List;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PurchasedOrderEmailDto {

    private String orderId;
    private String buyerName;
    private String email;
    private List<Product> orderedProductList;
    private Integer beforeTaxAmount;
    private Double estimatedTax;

}
