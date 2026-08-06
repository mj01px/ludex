package com.ludex.backend.pricing;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class WishlistId implements Serializable {

    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "game_id")
    private UUID gameId;

    public WishlistId(UUID userId, UUID gameId) {
        this.userId = userId;
        this.gameId = gameId;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof WishlistId that)) {
            return false;
        }
        return Objects.equals(userId, that.userId) && Objects.equals(gameId, that.gameId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, gameId);
    }
}
