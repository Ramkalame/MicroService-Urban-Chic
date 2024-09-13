package com.urbanchic.dto;

import com.urbanchic.entity.helper.VariantAttribute;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VariantDto {

    private List<VariantAttributeDto> variantAttributeDtoList;
    private int variantPrice;
    private int variantQuantity;

}
