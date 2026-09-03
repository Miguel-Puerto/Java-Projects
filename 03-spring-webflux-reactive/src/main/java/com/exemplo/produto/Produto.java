package com.exemplo.produto.model;

import io.swagger.v3.oas.annotations.media.Schema;

public class Produto {

    @Schema(description = "ID do produto", example = "1")
    private String id;

    @Schema(description = "Nome do produto", example = "Teclado Mecânico")
    private String nome;

    @Schema(description = "Preço em Reais", example = "250.00")
    private Double preco;

    @Schema(description = "Quantidade em estoque", example = "15")
    private Integer quantidadeEstoque;

    public Produto() {}

    public Produto(String id, String nome, Double preco, Integer quantidadeEstoque) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.quantidadeEstoque = quantidadeEstoque;
    }

    // Getters e Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public Double getPreco() { return preco; }
    public void setPreco(Double preco) { this.preco = preco; }

    public Integer getQuantidadeEstoque() { return quantidadeEstoque; }
    public void setQuantidadeEstoque(Integer quantidadeEstoque) { this.quantidadeEstoque = quantidadeEstoque; }
}