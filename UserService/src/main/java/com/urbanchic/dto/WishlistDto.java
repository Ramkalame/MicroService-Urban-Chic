package com.urbanchic.dto;

import com.urbanchic.entity.helper.WishlistItem;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WishlistDto {

    @NotBlank(message = "buyer id is mandatory")
    private String buyerId;
    private List<WishlistItemDto> wishlistItemList;
}
