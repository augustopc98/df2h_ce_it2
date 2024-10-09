package com.example.demo.models;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long productId;
    private int quantity;
    private BigDecimal productPrice;

    public OrderItem() {
    }

    public OrderItem(Long productId, int quantity, BigDecimal productPrice) {
        this.productId = productId;
        this.quantity = quantity;
        this.productPrice = productPrice;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public BigDecimal getPrice() {
        return productPrice.multiply(new BigDecimal(quantity));
    }
}
