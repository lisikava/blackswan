package com.project.fin.dto;

import com.project.fin.models.Shop;

public class CreateShopDto {
    private String name;
    private String description;
    private String slug;
    private boolean aiTags;
    public void setDescription(String description) {
        this.description = description;
    }
    public void setName(String name) {
        this.name = name;
    }

    public boolean isAiTags() {
        return aiTags;
    }

    public void setAiTags(boolean aiTags) {
        this.aiTags = aiTags;
    }

    public String getDescription() {
        return description;
    }
    public String getName() {
        return name;
    }
    public String getSlug() {
        return slug;
    }
    public void setSlug(String slug) {
        this.slug = slug;
    }

    public static CreateShopDto toDto(Shop shop) {
        CreateShopDto dto = new CreateShopDto();
        dto.setName(shop.getName());
        dto.setDescription(shop.getDescription());
        dto.setSlug(shop.getSlug());
        dto.setAiTags(shop.isAiTags());
        return dto;
    }
}
