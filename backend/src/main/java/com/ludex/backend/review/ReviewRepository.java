package com.ludex.backend.review;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, UUID> {

    Page<Review> findByGameId(UUID gameId, Pageable pageable);

    List<Review> findByUserId(UUID userId);

    Optional<Review> findByGameIdAndUserId(UUID gameId, UUID userId);
}
