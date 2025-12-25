package com.project.fin.repositories;

import com.project.fin.models.Shop;
import com.project.fin.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ShopRepository extends JpaRepository<Shop, Long> {
//    @Query("SELECT s FROM Shop s WHERE s.owner_id = ?1")
    Optional<Shop> findByOwnerId(Long ownerId);
    Optional<Shop> findBySlug(String slug);
}
