package com.prashant.orderservice.service;

import com.prashant.orderservice.model.Order;

public interface OrderService {

    Order createOrder(Order order);

    Order orderByProductId(int id);
}
