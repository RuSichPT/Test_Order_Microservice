package com.github.RuSichPT.TestOrderMicroservice.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderItemMapper orderItemMapper;

    public void createOrder(Order order)
    {
        orderMapper.createOrder(order.getOrderStatusId(), order.getCustomerName(),
                order.getCustomerPhone(), order.getCustomerComment());
        for (OrderItem orderItem: order.getOrderItems())
        {
            orderItemMapper.createOrder(orderItem.getItemName());
        }
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

    public void updateOrder(int id, Order order)
    {
        orderMapper.updateOrder(id, order.getOrderStatusId(), order.getCustomerName(),
                order.getCustomerPhone(), order.getCustomerComment());
        for (OrderItem orderItem: order.getOrderItems())
        {
            orderItemMapper.updateByOrderId(id, orderItem.getItemName());
        }
    }

    public void deleteOrder(int id)
    {
        orderItemMapper.deleteOrderByOrderId(id);
        orderMapper.deleteOrder(id);
    }
}
