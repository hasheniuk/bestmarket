package com.bestmarket.service.impl;

import com.bestmarket.entity.*;
import com.bestmarket.repository.OrderRepository;
import com.bestmarket.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.*;
import java.util.List;

@Service
@Transactional(propagation= Propagation.REQUIRED, readOnly=true)
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Order> findByUser(User user) {
        return orderRepository.findByUser(user);
    }
}
