package com.dev.arthur.games_tracker.services;

import com.dev.arthur.games_tracker.controllers.dtos.BestDealDTO;
import com.dev.arthur.games_tracker.controllers.dtos.PriceResultDTO;
import com.dev.arthur.games_tracker.controllers.dtos.SearchResultDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Comparator;

@Service
public class GameAPIService {
    private final WebClient webClient = WebClient.create("https://api.isthereanydeal.com");


    @Value("${api.key}")
    private String apiKey;

    private Mono<String> findGameIdByTitle(String title) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/games/search/v1")
                        .queryParam("key", apiKey)
                        .queryParam("title", title)
                        .build())
                .retrieve()
                .bodyToFlux(SearchResultDTO.class)
                .next()
                .map(SearchResultDTO::id);
    }

    private BestDealDTO extractBestDeal(PriceResultDTO priceResultDTO) {
        return priceResultDTO.getDeals().stream()
                .min(Comparator.comparing(dealDTO -> dealDTO.getPrice().getAmount()))
                .map(dealDTO -> new BestDealDTO(
                        dealDTO.getShop().getName(),
                        dealDTO.getPrice().getAmount(),
                        dealDTO.getUrl()
                ))
                .orElse(null);
    }

    private Mono<BestDealDTO> findBestDealById(String id) {
        return webClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path("/games/prices/v3")
                        .queryParam("key", apiKey)
                        .queryParam("country", "BR")
                        .build())
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new String[]{id})
                .retrieve()
                .bodyToFlux(PriceResultDTO.class)
                .next()
                .mapNotNull(this::extractBestDeal);
    }

    public Mono<BestDealDTO> findBestDealByTitle(String title) {
        return findGameIdByTitle(title).flatMap(this::findBestDealById);
    }

}
