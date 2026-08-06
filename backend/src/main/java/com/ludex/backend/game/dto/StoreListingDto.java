package com.ludex.backend.game.dto;

import java.math.BigDecimal;
import java.time.Instant;

public record StoreListingDto(
        String store,
        BigDecimal price,
        String currency,
        String externalUrl,
        Instant capturedAt) {
}
