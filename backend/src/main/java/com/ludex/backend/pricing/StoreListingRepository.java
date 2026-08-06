package com.ludex.backend.pricing;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreListingRepository extends JpaRepository<StoreListing, UUID> {

    List<StoreListing> findByGameId(UUID gameId);

    List<StoreListing> findByGameIdIn(List<UUID> gameIds);

    Optional<StoreListing> findByGameIdAndStore(UUID gameId, Store store);
}
