package com.project.fin.dto;

import com.project.fin.models.Order;
import com.project.fin.models.OrderStatus;

public class ArtistOrderDto extends OrderDto {
    private String buyerEmail;
    private OrderStatus status;
    public ArtistOrderDto() {}

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public String getBuyerEmail() {
        return buyerEmail;
    }
    public void setBuyerEmail(String buyerEmail) {
        this.buyerEmail = buyerEmail;
    }
    public static ArtistOrderDto toDto(Order order) {
        ArtistOrderDto dto = new ArtistOrderDto();
        dto.setFullName(order.getFullName());
        dto.setCity(order.getCity());
        dto.setCountry(order.getCountry());
        dto.setAddress(order.getAddress());
        dto.setPhone(order.getPhone());
        dto.setId(order.getId());
        dto.setBuyerEmail(order.getBuyer().getEmail());
        dto.setStatus(order.getStatus());
        return dto;
    }
}
