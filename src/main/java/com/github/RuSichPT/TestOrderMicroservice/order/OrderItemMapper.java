package com.github.RuSichPT.TestOrderMicroservice.order;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrderItemMapper {

    final String insert =
            "INSERT INTO \"order_item\"(ID, ORDER_ID, ITEM_NAME)" +
            "VALUES (#{orderId}, CURRVAL('order_seq'), #{itemName});";
    final String selectOrderId = "SELECT * FROM \"order_item\" WHERE order_id = #{order_id}";

    @Insert()
    public void createOrder(int orderId, String itemName);

    @Select(selectOrderId)
    public List<OrderItem> getOrderItemByOrderId(long order_id);

}
