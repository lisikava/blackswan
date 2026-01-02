package com.project.fin.dto;

import com.project.fin.models.Artwork;
import com.project.fin.models.ArtworkStatus;

import java.math.BigDecimal;
import java.util.List;

public class PublicArtworkDto extends ArtworkDto {
    private String shopSlug;
    private String shopName;
    public PublicArtworkDto() {};

    public String getShopSlug() {
        return shopSlug;
    }
    public String getShopName() {
        return shopName;
    }
    public void setShopSlug(String shopSlug) {
        this.shopSlug = shopSlug;
    }
    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public static PublicArtworkDto toDto(Artwork artwork) {
        PublicArtworkDto dto = new PublicArtworkDto();
        dto.setTitle(artwork.getTitle());
        dto.setDescription(artwork.getDescription());
        dto.setPrice(artwork.getPrice());
        dto.setTags(artwork.getTags());
        dto.setStatus(artwork.getStatus());
        dto.setImageUrl(artwork.getImageUrl());
        dto.setId(artwork.getId());
        dto.setShopSlug(artwork.getShop().getSlug());
        dto.setShopName(artwork.getShop().getName());
        dto.setType(artwork.getType());
        return dto;
    }
}
