package com.github.RuSichPT.TestOrderMicroservice.services;

import com.github.RuSichPT.TestOrderMicroservice.mappers.OrderItemMapper;
import com.github.RuSichPT.TestOrderMicroservice.mappers.OrderMapper;
import com.github.RuSichPT.TestOrderMicroservice.order.Order;
import com.github.RuSichPT.TestOrderMicroservice.order.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

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

        List<OrderItem> oldOI = oldOrder.getOrderItems();
        List<OrderItem> newOI = newOrder.getOrderItems();

        for (OrderItem newOderItem: newOI)
        {
            if (isExistingId(oldOI, newOderItem.getId()))
            {
                orderItemMapper.updateOrderItemByOrderId(newOderItem);
            }
            else
            {
                orderItemMapper.insertOrderItemByOrderId(newOrder.getId(), newOderItem.getItemName());
            }
        }

        for (OrderItem oldOderItem: oldOI)
        {
            if (!isExistingId(newOI, oldOderItem.getId()))
            {
                orderItemMapper.deleteOrderItem(oldOderItem);
            }
        }
    }
    @Override
    public void delete(int id)
    {
        orderItemMapper.deleteOrderItemByOrderId(id);
        orderMapper.deleteOrder(id);
    }

    private boolean isExistingId(List<OrderItem> orderItemList, int id)
    {
        for (OrderItem item: orderItemList)
        {
            if (item.getId() == id)
            {
                return  true;
            }
        }

        return false;
    }
}
