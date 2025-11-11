package com.dev.arthur.games_tracker.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@RequestMapping("/test")
public class TestController {

    @Value("${api.key}")
    private String apiKey;

    private final WebClient webClient = WebClient.create("https://api.isthereanydeal.com");

    @GetMapping("/1")
    public String test() {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/games/search/v1")
                        .queryParam("key", "apiKey")
                        .queryParam("title", "elden ring")
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .block(); // bloqueia até obter resposta (útil para testes)
    }

    @PostMapping("/prices")
    public String preco() {
        return webClient.post()
                .uri(uriBuilder -> uriBuilder.path("/games/prices/v3")
                        .queryParam("key", apiKey)
                        .queryParam("country", "BR")
                        .build())
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new String[]{"018d937f-590c-728b-ac35-38bcff85f086"})
                .retrieve()
                .bodyToMono(String.class)
                .block(); // bloqueia até obter resposta (útil para testes)
    }
}

