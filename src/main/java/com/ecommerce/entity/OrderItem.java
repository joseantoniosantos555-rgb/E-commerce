package com.ecommerce.entity;

import java.math.BigDecimal;

public class OrderItem {
    private Integer quantity;
    private BigDecimal price;
    private BigDecimal subtotal;
    private SimpleProduct product;

    // Getters and Setters
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public BigDecimal getSubtotal() { return subtotal; }
    public void setSubtotal(BigDecimal subtotal) { this.subtotal = subtotal; }

    public SimpleProduct getProduct() { return product; }
    public void setProduct(SimpleProduct product) { this.product = product; }

    public static class SimpleProduct {
        private String nome;

        public String getNome() { return nome; }
        public void setNome(String nome) { this.nome = nome; }
    }
}