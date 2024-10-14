package com.urbanchic.entity;

import com.urbanchic.entity.helper.WishlistItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "wishlist_details_collections")
public class Wishlist {

    @Id
    private String id;
    private String buyerId;
    private List<WishlistItem> wishlistItemList;

}
