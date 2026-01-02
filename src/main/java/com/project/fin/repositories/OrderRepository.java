package com.project.fin.repositories;

import com.project.fin.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<List<Order>> findByShopId(Long shopId);
    Optional<List<Order>> findByBuyerId(Long buyerId);
}
