package com.github.RuSichPT.TestOrderMicroservice.mappers;

import com.github.RuSichPT.TestOrderMicroservice.order.OrderItem;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OrderItemMapper {

    void insertOrderItem(String itemName);

    void insertOrderItemByOrderId(int orderId, String itemName);

    List<OrderItem> selectOrderItemByOrderId(int orderId);

    void updateOrderItemByOrderId(OrderItem orderItem);

    void deleteOrderItemByOrderId(int orderId);

    void deleteOrderItem(OrderItem orderItem);

}
