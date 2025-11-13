package com.dev.arthur.games_tracker.controllers.dtos;

import lombok.Getter;

import java.util.List;

@Getter
public class PriceResultDTO {
    private String id;
    private List<DealDTO> deals;

    @Getter
    public static class DealDTO {
        private ShopDTO shop;
        private PriceDTO price;
        private String url;
    }

    @Getter
    public static class ShopDTO {
        private String name;
    }

    @Getter
    public static class PriceDTO {
        private double amount;
    }
}

