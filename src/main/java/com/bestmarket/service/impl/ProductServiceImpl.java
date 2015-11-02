package com.bestmarket.service.impl;

import com.bestmarket.entity.*;
import com.bestmarket.service.ProductService;
import com.bestmarket.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.*;

import java.util.List;

@Service
@Transactional(propagation= Propagation.REQUIRED, readOnly=true)
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> findAllAvailable() {
        return productRepository.findAllAvailable();
    }

    @Override
    public List<Product> findAvailableByCategory(Category category) {
        return productRepository.findAvailableByCategory(category);
    }

    @Override
    public Product findByName(String name) {
        return productRepository.findByName(name);
    }
}
