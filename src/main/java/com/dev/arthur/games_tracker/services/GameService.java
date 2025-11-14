package com.dev.arthur.games_tracker.services;

import com.dev.arthur.games_tracker.entities.Game;
import com.dev.arthur.games_tracker.repositories.GameRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {

    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public List<Game> getGames() {
        return gameRepository.findAll();
    }

    public Optional<Game> getGame(String id) {
        return gameRepository.findById(id);
    }

    public Game saveGame(Game game) {
        return gameRepository.save(game);
    }

    public Game updateGame(Game game) {
        Game existingGame = gameRepository.findById(game.getId()).orElse(null);

        assert existingGame != null;
        existingGame.setTitle(game.getTitle());
        existingGame.setScore(game.getScore());
        existingGame.setYear(game.getYear());
        existingGame.setGenre(game.getGenre());
        existingGame.setNotes(game.getNotes());
        existingGame.setPlatform(game.getPlatform());

        return gameRepository.save(existingGame);
    }

    public void deleteGame(String id) {
        gameRepository.deleteById(id);
    }
}
