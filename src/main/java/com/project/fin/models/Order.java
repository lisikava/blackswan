package com.project.fin.models;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "buyer_id", nullable = false)
    private User buyer;
    @OneToOne
    @JoinColumn(name = "artwork_id", nullable = false)
    private Artwork artwork;
    @OneToOne
    @JoinColumn(name = "shop_id", nullable = false)
    private Shop shop;
    private BigDecimal price;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    private LocalDateTime createdAt;

    private String fullName;
    private String address;
    private String city;
    private String country;
    private String zipCode;
    private String phone;

    public Long getId() {
        return id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Artwork getArtwork() {
        return artwork;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public User getBuyer() {
        return buyer;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPhone() {
        return phone;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setArtwork(Artwork artwork) {
        this.artwork = artwork;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }



}
