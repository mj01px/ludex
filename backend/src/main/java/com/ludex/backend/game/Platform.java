package com.ludex.backend.game;

import com.ludex.backend.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "platform")
@Getter
@Setter
@NoArgsConstructor
public class Platform extends BaseEntity {

    @Column(nullable = false, unique = true, length = 100)
    private String name;

    public Platform(String name) {
        this.name = name;
    }
}
