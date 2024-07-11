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
@Document(collection = "wishlist_collections")
public class Wishlist {

    @Id
    private String wishlistItemId;
    private String productId;
    private String mobileNo;

}
