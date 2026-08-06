package com.ludex.backend.pricing;

import com.ludex.backend.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.Instant;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "price_history")
@Getter
@Setter
@NoArgsConstructor
public class PriceHistory extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "store_listing_id", nullable = false)
    private StoreListing storeListing;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(nullable = false, length = 3)
    private String currency;

    @Column(name = "captured_at", nullable = false)
    private Instant capturedAt = Instant.now();

    public PriceHistory(StoreListing storeListing, BigDecimal price, String currency) {
        this.storeListing = storeListing;
        this.price = price;
        this.currency = currency;
    }
}
