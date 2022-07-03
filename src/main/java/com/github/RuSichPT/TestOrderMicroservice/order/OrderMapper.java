package com.github.RuSichPT.TestOrderMicroservice.order;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface OrderMapper {
    @Select("SELECT * FROM \"order\"")
    List<Order> getOrders();

    @Select("SELECT * FROM \"order_item\"")
    List<OrderItem> getOrderItems();

    @Select("SELECT * FROM \"order\" WHERE id = #{id}")
    Order getOrderById(long id);

    @Select("SELECT * FROM \"order_item\" WHERE order_id = #{order_id}")
    List<OrderItem> getOrderItemByOrderId(long order_id);

    @Insert("INSERT INTO \"order\" (order_status_id, customer_name, customer_phone, customer_comment)" +
            " VALUES (#{order_status_id}, #{customer_name}, #{customer_phone}, #{customer_comment})")
    void createOrder(long order_status_id, String customer_name, String customer_phone, String customer_comment);

    //@Insert("INSERT INTO \"order_item\" VALUES (#{})")

    @Delete("DELETE FROM ORDER WHERE id = #{id}")
    Integer deleteOrderById(long id);
}
