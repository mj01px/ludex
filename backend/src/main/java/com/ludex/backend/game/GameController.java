package com.ludex.backend.game;

import com.ludex.backend.common.web.ApiResponse;
import com.ludex.backend.common.web.PagedResponse;
import com.ludex.backend.game.dto.GameDetailDto;
import com.ludex.backend.game.dto.GameSummaryDto;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/games")
public class GameController {

    private static final int MAX_PER_PAGE = 100;

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    public PagedResponse<GameSummaryDto> list(
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int perPage) {
        int size = Math.min(Math.max(perPage, 1), MAX_PER_PAGE);
        var pageable = PageRequest.of(Math.max(page, 0), size, Sort.by("name").ascending());
        return PagedResponse.of(gameService.search(search, pageable));
    }

    @GetMapping("/{slug}")
    public ApiResponse<GameDetailDto> detail(@PathVariable String slug) {
        return ApiResponse.of(gameService.getBySlug(slug));
    }
}
