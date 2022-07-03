package com.github.RuSichPT.TestOrderMicroservice.order;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    private final OrderMapper orderMapper;

    public OrderService(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    public List<Order> getOrders()
    {
        return orderMapper.getOrders();
    }

    public List<OrderItem> getOrderItems()
    {
        return orderMapper.getOrderItems();
    }

    public List<OrderItem> getOrderItemByOrderId(long order_id)
    {
        return orderMapper.getOrderItemByOrderId(order_id);
    }

    public Order getOrderById(long id)
    {
        Order order = orderMapper.getOrderById(id);
        if (order != null)
        {
            order.setOrderItems(orderMapper.getOrderItemByOrderId(order.getId()));
        }
        return order;
    }



//    public Order createOrder(Order order)
//    {
////        orderMapper.createOrder(order.getOrder_status_id(), order.getCustomer_name(), order.getCustomer_phone(), order.getCustomer_comment());
////        return getOrderById()
//    }
}
