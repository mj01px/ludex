package com.ludex.backend.pricing;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceHistoryRepository extends JpaRepository<PriceHistory, UUID> {

    List<PriceHistory> findByStoreListingIdOrderByCapturedAtDesc(UUID storeListingId);
}
