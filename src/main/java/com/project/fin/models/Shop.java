package com.project.fin.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "shops")
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false, unique = true)
    private String slug;
    private String description;
    @OneToOne
    @JoinColumn(name = "owner_id", nullable = false, unique = true)
    private User owner;
    @OneToMany(mappedBy = "shop", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Artwork> artworks = new ArrayList<>();

    public Shop() {}
    public Shop(String name, String description, User owner) {
        this.name = name;
        this.slug = Shop.generateSlug(name);
        this.description = description;
        this.owner = owner;
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
    public User getOwner() {
        return owner;
    }
    public String getSlug() {
        return slug;
    }

    public List<Artwork> getArtworks() {
        return artworks;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setOwner(User owner) {
        this.owner = owner;
    }
    public void setSlug(String slug) {
        this.slug = slug;
    }
    public void setArtworks(List<Artwork> artworks) {
        this.artworks = artworks;
    }
    public void addArtwork(Artwork artwork) {
        this.artworks.add(artwork);
        artwork.setShop(this);
    }
    public static String generateSlug(String name) {
        return name.toLowerCase().replaceAll("[^a-z0-9]", "-").replaceAll("-+", "-").replaceAll("^-|-$", "");
    }

    @Override
    public String toString() {
        return "Shop{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", owner=" + owner +
                ", artworks=" + artworks +
                '}';
    }
}
