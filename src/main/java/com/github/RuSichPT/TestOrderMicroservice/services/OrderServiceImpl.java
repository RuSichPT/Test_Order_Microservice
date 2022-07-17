package com.github.RuSichPT.TestOrderMicroservice.services;

import com.github.RuSichPT.TestOrderMicroservice.mappers.OrderItemMapper;
import com.github.RuSichPT.TestOrderMicroservice.mappers.OrderMapper;
import com.github.RuSichPT.TestOrderMicroservice.order.Order;
import com.github.RuSichPT.TestOrderMicroservice.order.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

        ArrayList<OrderItem> oldOI = (ArrayList<OrderItem>) oldOrder.getOrderItems();
        ArrayList<OrderItem> newOI = (ArrayList<OrderItem>) newOrder.getOrderItems();

        for (OrderItem newOderItem: newOI)
        {
            OrderItem oldOrderItem = isExistingId(oldOI, newOderItem.getId());
            if (oldOrderItem != null)
            {
                orderItemMapper.updateOrderItemByOrderId(newOderItem);
                oldOI.remove(oldOrderItem);
                newOI.remove(newOderItem);
            }
        }

        orderItemMapper.insertOrderItemsByOrderId(newOrder.getId(), newOI);
        orderItemMapper.deleteOrderItems(oldOI);
    }
    @Override
    public void delete(int id)
    {
        orderMapper.deleteOrder(id);
    }

    private OrderItem isExistingId(List<OrderItem> orderItemList, int id)
    {
        for (OrderItem item: orderItemList)
        {
            if (item.getId() == id)
            {
                return item;
            }
        }

        return null;
    }


}
