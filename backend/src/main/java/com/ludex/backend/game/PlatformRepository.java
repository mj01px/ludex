package com.ludex.backend.game;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlatformRepository extends JpaRepository<Platform, UUID> {

    Optional<Platform> findByName(String name);
}
