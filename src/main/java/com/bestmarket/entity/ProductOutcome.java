package com.bestmarket.entity;

import javax.persistence.*;

@Entity
@Table(name = "product_outcome")
public class ProductOutcome {

    @Id @GeneratedValue
    @Column(name = "product_outcome_id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "product", nullable = false)
    private Product product;

    @Column(name = "count")
    private int count;

    @ManyToOne
    @JoinColumn(name = "user_order", nullable = false)
    private Order order;

    public ProductOutcome() {}

    public ProductOutcome(Product product, int count, Order order) {
        this.product = product;
        this.count = count;
        this.order = order;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
