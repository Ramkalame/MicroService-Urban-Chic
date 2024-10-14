package com.urbanchic.external.helper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VariantAttribute {

    @Id
    private String variantAttributeId;
    private String variantAttributeName;
    private Object variantAttributeValue;
}
