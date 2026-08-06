package com.ludex.backend.game;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, UUID> {

    @Override
    @EntityGraph(attributePaths = {"genres", "platforms"})
    Page<Game> findAll(Pageable pageable);

    @EntityGraph(attributePaths = {"genres", "platforms"})
    Page<Game> findByNameContainingIgnoreCase(String name, Pageable pageable);

    @EntityGraph(attributePaths = {"genres", "platforms"})
    Optional<Game> findBySlug(String slug);

    Optional<Game> findBySteamAppId(Long steamAppId);
}
