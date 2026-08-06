package com.ludex.backend.game.dto;

import java.math.BigDecimal;

public record PriceDto(String store, BigDecimal price, String currency) {
}
