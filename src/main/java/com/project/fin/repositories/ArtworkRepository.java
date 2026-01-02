package com.project.fin.repositories;
import jakarta.persistence.LockModeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.project.fin.models.Artwork;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ArtworkRepository extends JpaRepository<Artwork, Long> {
    Optional<List<Artwork>> findByShopId(Long shopId);
    @Query("SELECT a FROM Artwork a ORDER BY random()")
    Page<Artwork> findRandom(Pageable pageable);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT a FROM Artwork a WHERE a.id = :id")
    Optional<Artwork> findByIdWithLock(Long id);

    @Query(value = "SELECT * FROM artworks WHERE :tag=ANY(tags)", nativeQuery = true)
    Page<Artwork> findByTag(@Param("tag") String tag, Pageable pageable);
}
