package com.ak.demo.order.service;

import com.ak.demo.order.entity.Order;

public interface OrderService {
    void create(Order order);

    void doCreate(Order order);

    Order get(Long id);
}
