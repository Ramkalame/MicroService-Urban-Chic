package com.urbanchic.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "review_collections")
public class Review {

    @Id
    private String reviewId;
    private String reviewDescription;
    private int rating;
    private String productId;
    private String userId;
}
