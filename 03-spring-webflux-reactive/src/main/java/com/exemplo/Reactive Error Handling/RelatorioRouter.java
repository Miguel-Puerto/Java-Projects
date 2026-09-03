package com.exemplo.relatorio;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.stream.Stream;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RelatorioRouter {

    @Bean
    public RouterFunction<ServerResponse> relatorioRoute() {
        return route(GET("/api/relatorios/stream"), request -> 
            ServerResponse.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(
                    Flux.fromStream(Stream.generate(() -> "Linha de relatório processada - " + System.currentTimeMillis()))
                        .delayElements(Duration.ofMillis(500))
                        .take(10), // Processa até 10 itens sem travar a memória
                    String.class
                )
        );
    }
}