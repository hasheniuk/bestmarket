package com.bestmarket.entity;

import com.bestmarket.util.persistence.converter.CategoryConverter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue
    @Column(name = "product_id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "picture")
    private String picture;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "count")
    private int count;

    @Convert(converter = CategoryConverter.class)
    @Column(name = "category", nullable = false)
    private Category category;

    @Column(name = "description")
    private String description;

    public Product() {}

    public Product(String name, String picture, BigDecimal price, int count, Category category, String description) {
        this.name = name;
        this.picture = picture;
        this.price = price;
        this.count = count;
        this.category = category;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (!name.equals(product.name)) return false;
        return price.equals(product.price);

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + price.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", picture='" + picture + '\'' +
                ", price=" + price +
                ", count=" + count +
                ", category=" + category +
                ", description='" + description + '\'' +
                '}';
    }
}
