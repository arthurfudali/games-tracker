package com.dev.arthur.games_tracker.controllers;

import com.dev.arthur.games_tracker.controllers.dtos.BestDealDTO;
import com.dev.arthur.games_tracker.entities.Game;
import com.dev.arthur.games_tracker.services.GameAPIService;
import com.dev.arthur.games_tracker.services.GameService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/games")
public class GameController {
    private final GameAPIService gameAPIService;
    private final GameService gameService;

    public GameController(GameAPIService gameAPIService, GameService gameService) {
        this.gameAPIService = gameAPIService;
        this.gameService = gameService;
    }

    @GetMapping("/bestprice/{title}")
    public Mono<BestDealDTO> getBestPrice(@PathVariable String title) {
        return gameAPIService.findBestDealByTitle(title);
    }

    @PostMapping
    public Game create(@RequestBody Game game) {
        return gameService.saveGame(game);
    }

    @GetMapping
    public List<Game> list() {
        return gameService.getGames();
    }

    @GetMapping("/{id}")
    public Optional<Game> get(@PathVariable String id) {
        return gameService.getGame(id);
    }

    @PutMapping("/{id}")
    public Game update(@PathVariable String id, @RequestBody Game game) {
        return gameService.updateGame(game);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        gameService.deleteGame(id);
    }

}
