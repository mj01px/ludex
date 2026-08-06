package com.ludex.backend.game.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record GameDetailDto(
        UUID id,
        String slug,
        String name,
        String coverUrl,
        LocalDate releaseDate,
        List<String> genres,
        List<String> platforms,
        List<StoreListingDto> storeListings) {
}
