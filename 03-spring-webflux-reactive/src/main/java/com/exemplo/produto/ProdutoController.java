package com.exemplo.produto.controller;

import com.exemplo.produto.model.Produto;
import com.exemplo.produto.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/produtos")
@Tag(name = "Produtos", description = "Endpoints reativos para gerenciamento de produtos")
public class ProdutoController {

    private final ProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Cadastra um novo produto", description = "Retorna o produto criado")
    @ApiResponse(responseCode = "201", description = "Produto criado com sucesso")
    public Mono<Produto> criar(@RequestBody Produto produto) {
        return service.salvar(produto);
    }

    @GetMapping
    @Operation(summary = "Lista todos os produtos", description = "Retorna um fluxo reativo (Flux) de produtos")
    public Flux<Produto> listarTodos() {
        return service.buscarTodos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca produto por ID", description = "Retorna o produto ou 404 se não for encontrado")
    @ApiResponse(responseCode = "200", description = "Encontrado")
    @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    public Mono<ResponseEntity<Produto>> buscarPorId(@PathVariable String id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Remove produto por ID")
    public Mono<Void> deletar(@PathVariable String id) {
        return service.deletar(id);
    }
}
