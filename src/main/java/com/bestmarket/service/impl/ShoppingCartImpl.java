package com.bestmarket.service.impl;

import com.bestmarket.entity.Order;
import com.bestmarket.entity.Product;
import com.bestmarket.entity.ProductOutcome;
import com.bestmarket.entity.User;
import com.bestmarket.repository.ProductOutcomeRepository;
import com.bestmarket.repository.ProductRepository;
import com.bestmarket.repository.OrderRepository;
import com.bestmarket.service.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class ShoppingCartImpl implements ShoppingCart {
    private Map<Product, Integer> products = new LinkedHashMap<>();
    private BigDecimal totalPrice = new BigDecimal(0);
    private User user = null;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductOutcomeRepository productOutcomeRepository;

    public void setUser(User user) {
        this.user = user;
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    @Override
    public void add(Product product) {
        if (!products.containsKey(product)) {
            products.put(product, 1);
        } else {
            int count = products.get(product);
            products.put(product, ++count);
        }
        totalPrice = totalPrice.add(product.getPrice());
    }

    @Override
    public void remove(Product product) {
        int count = products.get(product);
        if (count > 1) {
            products.put(product, --count);
        } else {
            products.remove(product);
        }
        totalPrice = totalPrice.subtract(product.getPrice());
    }

    @Override
    @Transactional(readOnly = false)
    public List<ProductOutcome> order() {
        if (user == null) {
            throw new IllegalArgumentException("User was not defined");
        }

        List<ProductOutcome> boughtProducts = new LinkedList<>();

        Order order = new Order(LocalDateTime.now(), user, totalPrice);
        orderRepository.saveAndFlush(order);

        for (Map.Entry<Product, Integer> pair : products.entrySet()) {
            Product product = pair.getKey();
            int boughtCount = pair.getValue();
            int productCount = product.getCount();
            product.setCount(productCount - boughtCount);

            ProductOutcome productOutcome = new ProductOutcome(product, boughtCount, order);
            boughtProducts.add(productOutcome);
            productOutcomeRepository.saveAndFlush(productOutcome);
        }

        return boughtProducts;
    }

    @Override
    public void clear() {
        if (user == null) {
            throw new IllegalArgumentException("User was not defined");
        }

        products.clear();
        totalPrice = new BigDecimal(0);
    }
}
