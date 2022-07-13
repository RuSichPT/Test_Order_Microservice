package com.github.RuSichPT.TestOrderMicroservice.services;

import com.github.RuSichPT.TestOrderMicroservice.order.Order;
import com.github.RuSichPT.TestOrderMicroservice.order.OrderItem;
import com.github.RuSichPT.TestOrderMicroservice.mappers.OrderItemMapper;
import com.github.RuSichPT.TestOrderMicroservice.mappers.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        return orderMapper.selectOrder(id);
    }

    @Override
    public void update(int id, Order newOrder)
    {
        newOrder.setId(id);

        Order oldOrder = orderMapper.selectOrder(id);

        List<OrderItem> list = oldOrder.getOrderItems();

        for ()
        list.contains()

        orderMapper.updateOrder(newOrder);
        for (OrderItem orderItem: newOrder.getOrderItems())
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
