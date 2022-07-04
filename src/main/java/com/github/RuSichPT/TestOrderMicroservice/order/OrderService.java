package com.github.RuSichPT.TestOrderMicroservice.order;

import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;

    public OrderService(OrderMapper orderMapper, OrderItemMapper orderItemMapper) {
        this.orderMapper = orderMapper;
        this.orderItemMapper = orderItemMapper;
    }

    public void createOrder(Order order)
    {
        orderMapper.createOrder(order.getOrderStatusId(), order.getCustomerName(),
                order.getCustomerPhone(), order.getCustomerComment());
        orderItemMapper.
    }

    public Order getOrder(int id)
    {
        Order order = orderMapper.getOrder(id);
        if (order != null)
        {
            order.setOrderItems(orderItemMapper.getOrderItemByOrderId(order.getId()));
        }
        return order;
    }

    public void deleteOrder(long id)
    {
        orderMapper.deleteOrder(id);
    }
}
