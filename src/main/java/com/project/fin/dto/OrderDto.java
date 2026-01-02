package com.project.fin.dto;

import com.project.fin.models.Order;

public class OrderDto {
    private Long id;
    private String fullName;
    private String address;
    private String city;
    private String country;
    private String zipCode;
    private String phone;
    public OrderDto() {};

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public static OrderDto toDto(Order order) {
        OrderDto dto = new OrderDto();
        dto.setFullName(order.getFullName());
        dto.setCity(order.getCity());
        dto.setCountry(order.getCountry());
        dto.setAddress(order.getAddress());
        dto.setPhone(order.getPhone());
        dto.setId(order.getId());
        return dto;
    }
}
