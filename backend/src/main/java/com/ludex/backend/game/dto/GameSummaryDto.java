package com.ludex.backend.game.dto;

import java.util.List;
import java.util.UUID;

public record GameSummaryDto(
        UUID id,
        String slug,
        String name,
        String coverUrl,
        List<String> genres,
        List<String> platforms,
        PriceDto lowestPrice) {
}
