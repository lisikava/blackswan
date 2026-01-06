package com.project.fin.dto;

import com.project.fin.models.Order;
import com.project.fin.models.OrderStatus;

public class CustomerOrderDto extends OrderDto {
    private String shopName;
    private String imageUrl;
    private OrderStatus status;

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopName() {
        return shopName;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public static CustomerOrderDto toDto(Order order) {
        CustomerOrderDto dto = new CustomerOrderDto();
        dto.setFullName(order.getFullName());
        dto.setCity(order.getCity());
        dto.setCountry(order.getCountry());
        dto.setAddress(order.getAddress());
        dto.setPhone(order.getPhone());
        dto.setId(order.getId());
        dto.setShopName(order.getShop().getName());
        dto.setImageUrl(order.getArtwork().getImageUrl());
        dto.setStatus(order.getStatus());
        return dto;
    }
}
