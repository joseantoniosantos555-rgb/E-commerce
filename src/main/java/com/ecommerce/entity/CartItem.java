package com.ecommerce.entity;

import java.math.BigDecimal;

public class CartItem {
    private Product product;
    private Integer quantity;
    private BigDecimal subtotal;
    
    public CartItem() {}
    
    public CartItem(Product product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
        this.subtotal = product.getPreco().multiply(BigDecimal.valueOf(quantity));
    }
    
    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }
    
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { 
        this.quantity = quantity;
        if (this.product != null) {
            this.subtotal = this.product.getPreco().multiply(BigDecimal.valueOf(quantity));
        }
    }
    
    public BigDecimal getSubtotal() { return subtotal; }
    public void setSubtotal(BigDecimal subtotal) { this.subtotal = subtotal; }
    
    public String getProductName() { return product != null ? product.getNome() : null; }
    public Long getProductId() { return product != null ? product.getId() : null; }
}