package com.ecommerce.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class Order {
    private Long id;
    private LocalDateTime orderDate;
    private BigDecimal total;
    private String paymentMethod;
    private String deliveryAddress;
    private SimpleUser user;
    private List<OrderItem> items;
    private Long userId;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDateTime orderDate) { this.orderDate = orderDate; }

    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

    public String getDeliveryAddress() { return deliveryAddress; }
    public void setDeliveryAddress(String deliveryAddress) { this.deliveryAddress = deliveryAddress; }

    public SimpleUser getUser() { return user; }
    public void setUser(SimpleUser user) { this.user = user; }

    public List<OrderItem> getItems() { return items; }
    public void setItems(List<OrderItem> items) { this.items = items; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public static class SimpleUser {
        private String nomeCompleto;

        public String getNomeCompleto() { return nomeCompleto; }
        public void setNomeCompleto(String nomeCompleto) { this.nomeCompleto = nomeCompleto; }
    }

    public enum OrderStatus {
        AGUARDANDO, FATURADO, EM_SEPARACAO, ENVIADO, CONCLUIDO, CANCELADO
    }
}