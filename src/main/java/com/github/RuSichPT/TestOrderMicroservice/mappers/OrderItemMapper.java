package com.github.RuSichPT.TestOrderMicroservice.mappers;

import com.github.RuSichPT.TestOrderMicroservice.order.OrderItem;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderItemMapper {

    void insertOrderItem(String itemName);

    List<OrderItem> selectOrderItemByOrderId(int order_id);

    void updateOrderItemByOrderId(int order_id, String itemName);

    void deleteOrderItemByOrderId(int order_id);

}
