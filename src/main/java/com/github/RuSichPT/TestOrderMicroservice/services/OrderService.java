package com.github.RuSichPT.TestOrderMicroservice.services;

import com.github.RuSichPT.TestOrderMicroservice.entities.Order;

public interface OrderService {

    Order insert(Order order);

    Order select(int id);

    Order update(int id, Order order);

    void delete(int id);

}
