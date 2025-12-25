package com.project.fin.dto;

import com.project.fin.models.Artwork;
import com.project.fin.models.Shop;

import java.util.ArrayList;
import java.util.List;

public class PublicShopDto {
    private Long id;
    private String name;
    private String slug;
    private String description;
    private List<ArtworkDto> artworks;

    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setSlug(String slug) {
        this.slug = slug;
    }
    public void setArtworks(List<ArtworkDto> artworks) {
        this.artworks = artworks;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public String getSlug() {
        return slug;
    }
    public List<ArtworkDto> getArtworks() {
        return artworks;
    }
    public static PublicShopDto toDto(Shop shop) {
        PublicShopDto dto = new PublicShopDto();
        dto.setName(shop.getName());
        dto.setId(shop.getId());
        dto.setSlug(shop.getSlug());
        dto.setDescription(shop.getDescription());
        List<ArtworkDto> dtos = new ArrayList<>();
        for (Artwork artwork: shop.getArtworks()) {
            dtos.add(ArtworkDto.toDto(artwork));
        }
        dto.setArtworks(dtos);
        return dto;
    }
}

