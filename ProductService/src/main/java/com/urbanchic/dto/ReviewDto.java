package com.urbanchic.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDto {


    @NotBlank(message = "Review Description is mandatory")
    private String reviewDescription;
    @NotNull(message = "Rating is Mandatory")
    private Double rating;
    @NotBlank(message = "Product Id is mandatory")
    private String productId;
    @NotBlank(message = "User Id is mandatory")
    private String userId;

}
