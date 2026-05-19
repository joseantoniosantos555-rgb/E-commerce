package com.ecommerce.model;

import java.math.BigDecimal;

public class Product {
    private Long id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private Integer estoque;
    private String categoria;
    private String urlImagem;
    
    public Product() {}
    
    public Product(Long id, String nome, String descricao, BigDecimal preco, Integer estoque, String categoria) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.estoque = estoque;
        this.categoria = categoria;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    
    public BigDecimal getPreco() { return preco; }
    public void setPreco(BigDecimal preco) { this.preco = preco; }
    
    public Integer getEstoque() { return estoque; }
    public void setEstoque(Integer estoque) { this.estoque = estoque; }
    
    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    
    public String getUrlImagem() { return urlImagem; }
    public void setUrlImagem(String urlImagem) { this.urlImagem = urlImagem; }
}