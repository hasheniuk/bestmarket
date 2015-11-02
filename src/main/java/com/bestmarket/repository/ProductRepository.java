package com.bestmarket.repository;

import com.bestmarket.entity.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.count > 0")
    List<Product> findAllAvailable();

    @Query("SELECT p FROM Product p WHERE p.category = :category AND p.count > 0")
    List<Product> findAvailableByCategory(@Param("category") Category category);

    Product findByName(String name);
}
