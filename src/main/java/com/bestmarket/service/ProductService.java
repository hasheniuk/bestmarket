package com.bestmarket.service;

import com.bestmarket.entity.*;

import java.util.List;

public interface ProductService {
    List<Product> findAllAvailable();
    List<Product> findAvailableByCategory(Category category);
    Product findByName(String name);
}
