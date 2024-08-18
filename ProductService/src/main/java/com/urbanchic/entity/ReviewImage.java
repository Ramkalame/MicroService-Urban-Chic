package com.urbanchic.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "review_image_collections")
public class ReviewImage {

    @Id
    private String reviewImageId;
    private String reviewId;
    private String imageUrl;
}
