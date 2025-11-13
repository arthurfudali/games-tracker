package com.dev.arthur.games_tracker.controllers.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchResultDTO {
    private String id;
    private String slug;
    private String title;
    private boolean mature;

}
