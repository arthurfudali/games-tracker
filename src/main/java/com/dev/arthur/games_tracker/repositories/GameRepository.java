package com.dev.arthur.games_tracker.repositories;

import com.dev.arthur.games_tracker.entities.Game;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GameRepository extends MongoRepository<Game, String> {
}
