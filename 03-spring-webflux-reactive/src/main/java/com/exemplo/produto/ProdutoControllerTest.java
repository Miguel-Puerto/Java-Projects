package com.exemplo.produto;

import com.exemplo.produto.controller.ProdutoController;
import com.exemplo.produto.model.Produto;
import com.exemplo.produto.service.ProdutoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@WebFluxTest(ProdutoController.class)
public class ProdutoControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private ProdutoService service;

    @Test
    void deveBuscarProdutoPorIdComSucesso() {
        Produto produto = new Produto("1", "Teclado Mecânico", 250.0, 15);
        Mockito.when(service.buscarPorId("1")).thenReturn(Mono.just(produto));

        webTestClient.get()
                .uri("/api/produtos/1")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.nome").isEqualTo("Teclado Mecânico")
                .jsonPath("$.preco").isEqualTo(250.0);
    }

    @Test
    void deveRetornar404QuandoProdutoNaoExistir() {
        Mockito.when(service.buscarPorId("99")).thenReturn(Mono.empty());

        webTestClient.get()
                .uri("/api/produtos/99")
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void deveCadastrarProdutoComSucesso() {
        Produto novoProduto = new Produto("3", "Monitor 144Hz", 1200.0, 5);
        Mockito.when(service.salvar(Mockito.any(Produto.class))).thenReturn(Mono.just(novoProduto));

        webTestClient.post()
                .uri("/api/produtos")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(novoProduto)
                .exchange()
                .expectStatus().isCreated()
                .expectBody()
                .jsonPath("$.id").isEqualTo("3")
                .jsonPath("$.nome").isEqualTo("Monitor 144Hz");
    }
}
