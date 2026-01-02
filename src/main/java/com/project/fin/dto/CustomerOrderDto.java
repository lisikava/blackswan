package com.project.fin.dto;

import com.project.fin.models.Order;

public class CustomerOrderDto extends OrderDto {
    private String shopName;

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopName() {
        return shopName;
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
        return dto;
    }
}
