package com.bestmarket.service;

import com.bestmarket.entity.*;

import java.util.List;

public interface OrderService {
    List<Order> findByUser(User user);
}
