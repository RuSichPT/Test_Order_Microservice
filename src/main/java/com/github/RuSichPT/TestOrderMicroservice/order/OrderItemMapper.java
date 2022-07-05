package com.github.RuSichPT.TestOrderMicroservice.order;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderItemMapper {

    String insert = "INSERT INTO \"order_item\"(ID, ORDER_ID, ITEM_NAME)" +
                    "VALUES (NEXTVAL('order_item_seq'), CURRVAL('order_seq'), #{itemName});";
    String selectByOrderId = "SELECT * FROM \"order_item\" WHERE ORDER_ID = #{order_id}";
    String updateByOrderId = "UPDATE \"order_item\" " +
                             "SET ITEM_NAME = #{itemName} " +
                             "WHERE ORDER_ID = #{order_id}";
    String deleteByOrderId = "DELETE FROM \"order_item\" WHERE ORDER_ID = #{order_id}";

    @Insert(insert)
    void createOrder(String itemName);

    @Select(selectByOrderId)
    List<OrderItem> getOrderItemByOrderId(int order_id);

    @Update(updateByOrderId)
    void updateByOrderId(int order_id, String itemName);

    @Delete(deleteByOrderId)
    void deleteOrderByOrderId(int order_id);

}
