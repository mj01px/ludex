package com.ludex.backend.pricing;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishlistRepository extends JpaRepository<Wishlist, WishlistId> {

    List<Wishlist> findByIdUserId(UUID userId);
}
