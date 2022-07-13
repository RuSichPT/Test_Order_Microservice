package com.github.RuSichPT.TestOrderMicroservice.mappers;

import com.github.RuSichPT.TestOrderMicroservice.order.OrderItem;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OrderItemMapper {

    void insertOrderItem(String itemName);

    List<OrderItem> selectOrderItemByOrderId(int order_id);

    void updateOrderItemByOrderId(OrderItem orderItem);

    void deleteOrderItemByOrderId(int order_id);

}
