package com.exemplo.produto.service;

import com.exemplo.produto.model.Produto;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ProdutoService {

    private final Map<String, Produto> repositorio = new ConcurrentHashMap<>();

    public ProdutoService() {
        repositorio.put("1", new Produto("1", "Teclado Mecânico", 250.0, 15));
        repositorio.put("2", new Produto("2", "Mouse Gamer", 120.0, 30));
    }

    public Mono<Produto> salvar(Produto produto) {
        repositorio.put(produto.getId(), produto);
        return Mono.just(produto);
    }

    public Flux<Produto> buscarTodos() {
        return Flux.fromIterable(repositorio.values());
    }

    public Mono<Produto> buscarPorId(String id) {
        return Mono.justOrEmpty(repositorio.get(id));
    }

    public Mono<Void> deletar(String id) {
        repositorio.remove(id);
        return Mono.empty();
    }
}