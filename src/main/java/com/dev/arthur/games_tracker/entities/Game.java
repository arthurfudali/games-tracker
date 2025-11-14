package com.dev.arthur.games_tracker.entities;

import com.dev.arthur.games_tracker.entities.enums.Genre;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collation = "games")
public class Game {
    @Id
    private String id;

    private String title;
    private String platform;
    private Integer score;
    private String notes;
    private Integer year;
    private List<Genre> genre;

}
