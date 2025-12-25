package com.project.fin.repositories;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.project.fin.models.Artwork;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ArtworkRepository extends JpaRepository<Artwork, Long> {
    Optional<List<Artwork>> findByShopId(Long shopId);
    @Query(value = """
    SELECT a FROM Artwork a
    ORDER BY random()
    """)
    Page<Artwork> findRandom(Pageable pageable);
}
