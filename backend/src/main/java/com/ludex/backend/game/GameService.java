package com.ludex.backend.game;

import com.ludex.backend.game.dto.GameDetailDto;
import com.ludex.backend.game.dto.GameSummaryDto;
import com.ludex.backend.game.dto.PriceDto;
import com.ludex.backend.game.dto.StoreListingDto;
import com.ludex.backend.pricing.StoreListing;
import com.ludex.backend.pricing.StoreListingRepository;
import jakarta.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class GameService {

    private final GameRepository gameRepository;
    private final StoreListingRepository storeListingRepository;

    public GameService(GameRepository gameRepository, StoreListingRepository storeListingRepository) {
        this.gameRepository = gameRepository;
        this.storeListingRepository = storeListingRepository;
    }

    public Page<GameSummaryDto> search(String search, Pageable pageable) {
        Page<Game> games = (search == null || search.isBlank())
                ? gameRepository.findAll(pageable)
                : gameRepository.findByNameContainingIgnoreCase(search, pageable);

        List<UUID> gameIds = games.getContent().stream().map(Game::getId).toList();
        Map<UUID, StoreListing> lowestPriceByGameId = lowestPriceByGameId(gameIds);

        return games.map(game -> toSummaryDto(game, lowestPriceByGameId.get(game.getId())));
    }

    public GameDetailDto getBySlug(String slug) {
        Game game = gameRepository.findBySlug(slug)
                .orElseThrow(() -> new EntityNotFoundException("Game not found: " + slug));
        List<StoreListing> listings = storeListingRepository.findByGameId(game.getId());
        return toDetailDto(game, listings);
    }

    private Map<UUID, StoreListing> lowestPriceByGameId(List<UUID> gameIds) {
        if (gameIds.isEmpty()) {
            return Map.of();
        }
        Comparator<StoreListing> byPrice = Comparator.comparing(
                StoreListing::getCurrentPrice, Comparator.nullsLast(Comparator.naturalOrder()));

        return storeListingRepository.findByGameIdIn(gameIds).stream()
                .filter(listing -> listing.getCurrentPrice() != null)
                .collect(Collectors.groupingBy(
                        listing -> listing.getGame().getId(),
                        Collectors.collectingAndThen(
                                Collectors.minBy(byPrice),
                                optional -> optional.orElse(null))));
    }

    private GameSummaryDto toSummaryDto(Game game, StoreListing lowest) {
        return new GameSummaryDto(
                game.getId(),
                game.getSlug(),
                game.getName(),
                game.getCoverUrl(),
                sortedNames(game.getGenres().stream().map(g -> g.getName())),
                sortedNames(game.getPlatforms().stream().map(p -> p.getName())),
                lowest == null ? null : new PriceDto(lowest.getStore().name(), lowest.getCurrentPrice(), lowest.getCurrency()));
    }

    private GameDetailDto toDetailDto(Game game, List<StoreListing> listings) {
        List<StoreListingDto> storeListings = listings.stream()
                .sorted(Comparator.comparing(
                        StoreListing::getCurrentPrice, Comparator.nullsLast(Comparator.naturalOrder())))
                .map(listing -> new StoreListingDto(
                        listing.getStore().name(),
                        listing.getCurrentPrice(),
                        listing.getCurrency(),
                        listing.getExternalUrl(),
                        listing.getCapturedAt()))
                .toList();

        return new GameDetailDto(
                game.getId(),
                game.getSlug(),
                game.getName(),
                game.getCoverUrl(),
                game.getReleaseDate(),
                sortedNames(game.getGenres().stream().map(g -> g.getName())),
                sortedNames(game.getPlatforms().stream().map(p -> p.getName())),
                storeListings);
    }

    private List<String> sortedNames(java.util.stream.Stream<String> names) {
        return names.sorted().toList();
    }
}
