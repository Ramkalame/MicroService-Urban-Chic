package com.urbanchic.entity.helper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductImage {

    @Id
    private String productImageId;
    private String publicId;
    private String publicImageUrl;
}
