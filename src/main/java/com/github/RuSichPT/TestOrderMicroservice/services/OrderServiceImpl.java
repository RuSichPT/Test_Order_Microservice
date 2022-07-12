package com.github.RuSichPT.TestOrderMicroservice.services;

import com.github.RuSichPT.TestOrderMicroservice.order.Order;
import com.github.RuSichPT.TestOrderMicroservice.order.OrderItem;
import com.github.RuSichPT.TestOrderMicroservice.mappers.OrderItemMapper;
import com.github.RuSichPT.TestOrderMicroservice.mappers.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderItemMapper orderItemMapper;

    @Override
    public void insert(Order order)
    {
        orderMapper.insertOrder(order);
        for (OrderItem orderItem: order.getOrderItems())
        {
            orderItemMapper.insertOrderItem(orderItem.getItemName());
        }
    }

    @Override
    public Order select(int id)
    {
        Order order = orderMapper.selectOrder(id);
        if (order != null)
        {
            order.setOrderItems(orderItemMapper.selectOrderItemByOrderId(order.getId()));
        }
        return order;
    }

    @Override
    public void update(int id, Order order)
    {
        order.setId(id);
        orderMapper.updateOrder(order);
        for (OrderItem orderItem: order.getOrderItems())
        {
            orderItemMapper.updateOrderItemByOrderId(id, orderItem.getItemName());
        }
    }
    @Override
    public void delete(int id)
    {
        orderItemMapper.deleteOrderItemByOrderId(id);
        orderMapper.deleteOrder(id);
    }
}
