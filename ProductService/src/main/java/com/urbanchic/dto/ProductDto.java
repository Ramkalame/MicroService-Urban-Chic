package com.urbanchic.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    @NotBlank(message = "Product name is mandatory")
    private String productName;

    @NotBlank(message = "Product description is mandatory")
    private String productDescription;

    @NotBlank(message = "Product brand is mandatory")
    private String productBrand;

    @NotBlank(message = "Product category is mandatory")
    private String productCategory;

    @NotBlank(message = "Product sub-category is mandatory")
    private String productSubCategory;

    @NotBlank(message = "Product type is mandatory")
    private String productType;

    @NotBlank(message = "Seller ID is mandatory")
    private String sellerId;

    @NotNull(message = "Attribute List is mandatory")
    private List<AttributeDto> attributeDtoList;

    @NotNull(message = "Variant List is mandatory")
    private List<VariantDto> variantDtoList;

}
