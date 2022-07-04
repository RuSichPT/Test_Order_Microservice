package com.github.RuSichPT.TestOrderMicroservice.order;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface OrderMapper {

    final String insert =
            "INSERT INTO \"order\"(ID, ORDER_STATUS_ID, CUSTOMER_NAME, CUSTOMER_PHONE, CUSTOMER_COMMENT)" +
            "VALUES (NEXTVAL('order_seq'), #{orderStatusId}, #{customerName}, #{customerPhone}, #{customerComment});";
    final String selectId = "SELECT * FROM \"order\" WHERE id = #{id}";
    final String deleteId = "DELETE FROM \"order\" WHERE id = #{id}";

    @Insert(insert)
    public void createOrder(int orderStatusId, String customerName, String customerPhone, String customerComment);

    @Select(selectId)
    public Order getOrder(int id);

    @Delete(deleteId)
    public Integer deleteOrder(long id);
}
