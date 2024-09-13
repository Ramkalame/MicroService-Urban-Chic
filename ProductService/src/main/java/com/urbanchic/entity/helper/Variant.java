package com.urbanchic.entity.helper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Variant {

    @Id
    private String variantId;
    private List<VariantAttribute> variantAttributes;
    private int variantPrice;
    private int variantQuantity;
}
