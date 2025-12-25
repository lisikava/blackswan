package com.project.fin.dto;

import com.project.fin.models.Shop;

public class CreateShopDto {
    private String name;
    private String description;
    private String slug;
    public void setDescription(String description) {
        this.description = description;
    }
    public void setName(String name) {
        this.name = name;
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
        return dto;
    }
}
