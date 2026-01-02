package com.project.fin.models;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "artworks")

public class Artwork {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String imageUrl;
    private List<String> tags;
    private BigDecimal price;
    @Enumerated(EnumType.STRING)
    private ArtworkStatus status = ArtworkStatus.AVAILABLE;
    @Enumerated(EnumType.STRING)
    private ArtworkType type;
    @ManyToOne
    @JoinColumn(name = "shop_id", nullable = false)
    private Shop shop;
    public Artwork() {}
    public Artwork(String title, String description, BigDecimal price, String imageUrl, List<String> tags, Shop shop, ArtworkType type) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
        this.tags = tags;
        this.shop = shop;
        this.type = type;
    }
    public Long getId() {
        return id;
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
    public String getImageUrl() {
        return imageUrl;
    }
    public List<String> getTags() {
        return tags;
    }
    public Shop getShop() {
        return shop;
    }
    public ArtworkStatus getStatus() {
        return status;
    }
    public ArtworkType getType() {
        return type;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public void setTags(List<String> tags) {
        this.tags = tags;
    }
    public void setShop(Shop shop) {
        this.shop = shop;
    }
    public void setStatus(ArtworkStatus status) {
        this.status = status;
    }
    public void setType(ArtworkType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Artwork{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", tags=" + tags +
                ", price=" + price +
                ", shop=" + shop +
                '}';
    }
}
