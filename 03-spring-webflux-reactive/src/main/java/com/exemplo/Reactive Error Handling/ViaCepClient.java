package com.exemplo.client;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ViaCepClient {

    private final WebClient webClient;

    public ViaCepClient(WebClient.Builder builder) {
        this.webClient = builder.baseUrl("https://viacep.com.br/ws").build();
    }

    public Mono<String> buscarEnderecoPorCep(String cep) {
        return this.webClient.get()
                .uri("/{cep}/json/", cep)
                .retrieve()
                .bodyToMono(String.class)
                .onErrorResume(e -> Mono.just("{\"erro\": \"Falha ao buscar CEP\"}"));
    }
}