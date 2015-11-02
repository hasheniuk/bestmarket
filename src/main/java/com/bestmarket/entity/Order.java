package com.bestmarket.entity;

import com.bestmarket.util.persistence.converter.LocalDateTimeConverter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_order")
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "user_order_id")
    private long id;

    @Convert(converter = LocalDateTimeConverter.class)
    @Column(name = "order_date")
    private LocalDateTime orderDateTime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    public Order() {}

    public Order(LocalDateTime orderDateTime, User user, BigDecimal totalPrice) {
        this.orderDateTime = orderDateTime;
        this.user = user;
        this.totalPrice = totalPrice;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getOrderDateTime() {
        return orderDateTime;
    }

    public void setOrderDateTime(LocalDateTime orderDateTime) {
        this.orderDateTime = orderDateTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderDateTime=" + orderDateTime +
                ", user=" + user +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
