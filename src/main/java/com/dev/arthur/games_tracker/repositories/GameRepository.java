package com.dev.arthur.games_tracker.repositories;

import com.dev.arthur.games_tracker.entities.Game;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface GameRepository extends MongoRepository<Game, String> {
    List<Game> findGamesByTitleContainingIgnoreCase(String title);
}
