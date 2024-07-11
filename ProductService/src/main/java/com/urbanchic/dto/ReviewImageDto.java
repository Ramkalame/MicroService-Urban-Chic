package com.urbanchic.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewImageDto {

    @NotBlank(message = "Review Id is Mandatory")
    private String reviewId;
    @NotBlank(message = "Image URl is Mandatory")
    private String imageUrl;

}
