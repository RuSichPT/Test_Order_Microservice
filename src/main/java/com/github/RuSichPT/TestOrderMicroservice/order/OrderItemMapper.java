package com.github.RuSichPT.TestOrderMicroservice.order;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderItemMapper {

    void createOrder(String itemName);

    List<OrderItem> getOrderItemByOrderId(int order_id);

    void updateByOrderId(int order_id, String itemName);

    void deleteOrderByOrderId(int order_id);

}
