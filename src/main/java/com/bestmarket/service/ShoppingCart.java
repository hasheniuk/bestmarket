package com.bestmarket.service;

import com.bestmarket.entity.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface ShoppingCart {
    void setUser(User user);
    Map<Product, Integer> getProducts();
    BigDecimal getTotalPrice();
    void add(Product product);
    void remove(Product product);
    List<ProductOutcome> order();
    void clear();
}
