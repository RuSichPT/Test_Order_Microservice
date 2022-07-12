package com.github.RuSichPT.TestOrderMicroservice.services;

import com.github.RuSichPT.TestOrderMicroservice.order.Order;

public interface OrderService {

    void insert(Order order);

    Order select(int id);

    void update(int id, Order order);

    void delete(int id);

}
