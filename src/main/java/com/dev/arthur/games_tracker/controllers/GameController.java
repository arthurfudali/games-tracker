package com.dev.arthur.games_tracker.controllers;

import com.dev.arthur.games_tracker.controllers.dtos.BestDealDTO;
import com.dev.arthur.games_tracker.services.GameService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/games")
public class GameController {
    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/bestprice/{title}")
    public Mono<BestDealDTO> getBestPrice(@PathVariable String title) {
        return gameService.findBestDealByTitle(title);
    }
}
