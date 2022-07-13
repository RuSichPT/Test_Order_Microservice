package com.github.RuSichPT.TestOrderMicroservice.services;

import com.github.RuSichPT.TestOrderMicroservice.mappers.OrderItemMapper;
import com.github.RuSichPT.TestOrderMicroservice.mappers.OrderMapper;
import com.github.RuSichPT.TestOrderMicroservice.order.Order;
import com.github.RuSichPT.TestOrderMicroservice.order.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    public OrderMapper orderMapper;
    @Autowired
    public OrderItemMapper orderItemMapper;

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
        Order oldOrder = orderMapper.selectOrder(id);

        if (id != newOrder.getId() || (oldOrder == null))
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        orderMapper.updateOrder(newOrder);

        if (newOrder.getOrderItems().size() == oldOrder.getOrderItems().size())
        {
            for (OrderItem newOderItem: newOrder.getOrderItems())
            {
                if ())
                {

                }
                orderItemMapper.updateOrderItemByOrderId(orderItem);
            }
        }
        else if (newOrder.getOrderItems().size() > oldOrder.getOrderItems().size())
        {

        }
        else
        {

        }
    }
    @Override
    public void delete(int id)
    {
        orderItemMapper.deleteOrderItemByOrderId(id);
        orderMapper.deleteOrder(id);
    }
}
