package com.dev.arthur.games_tracker.controllers.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BestDealDTO {
    private String storeName;
    private double price;
    private String url;

}
