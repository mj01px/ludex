package com.ludex.backend.review;

import com.ludex.backend.common.SoftDeletableEntity;
import com.ludex.backend.game.Game;
import com.ludex.backend.user.AppUser;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Table(name = "review")
@SQLDelete(sql = "UPDATE review SET deleted_at = now() WHERE id = ?")
@SQLRestriction("deleted_at IS NULL")
@Getter
@Setter
@NoArgsConstructor
public class Review extends SoftDeletableEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private AppUser user;

    /** 1 to 5 stars. */
    @Column(nullable = false)
    private Short rating;

    private String body;

    public Review(Game game, AppUser user, Short rating, String body) {
        this.game = game;
        this.user = user;
        this.rating = rating;
        this.body = body;
    }
}
