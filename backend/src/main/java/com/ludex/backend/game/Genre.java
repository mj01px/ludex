package com.ludex.backend.game;

import com.ludex.backend.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "genre")
@Getter
@Setter
@NoArgsConstructor
public class Genre extends BaseEntity {

    @Column(nullable = false, unique = true, length = 100)
    private String name;

    public Genre(String name) {
        this.name = name;
    }
}
