package com.project.fin.dto;

import com.project.fin.models.Artwork;
import com.project.fin.models.ArtworkStatus;

import java.math.BigDecimal;
import java.util.List;

public class ArtworkDto {
    private Long id;
    private String title;
    private String description;
    private BigDecimal price;
    private List<String> tags;
    private ArtworkStatus status;
    private String imageUrl;
    public ArtworkDto() {};

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public List<String> getTags() {
        return tags;
    }
    public ArtworkStatus getStatus() {
        return status;
    }
    public String getImageUrl() {
        return imageUrl;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public void setTags(List<String> tags) {
        this.tags = tags;
    }
    public void setStatus(ArtworkStatus status) {
        this.status = status;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public static ArtworkDto toDto(Artwork artwork) {
        ArtworkDto dto = new ArtworkDto();
        dto.setTitle(artwork.getTitle());
        dto.setDescription(artwork.getDescription());
        dto.setPrice(artwork.getPrice());
        dto.setTags(artwork.getTags());
        dto.setStatus(artwork.getStatus());
        dto.setImageUrl(artwork.getImageUrl());
        dto.setId(artwork.getId());
        return dto;
    }
}
