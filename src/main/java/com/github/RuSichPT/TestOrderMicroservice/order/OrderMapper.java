package com.github.RuSichPT.TestOrderMicroservice.order;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface OrderMapper {

    String insert = "INSERT INTO \"order\"(ID, ORDER_STATUS_ID, CUSTOMER_NAME, CUSTOMER_PHONE, CUSTOMER_COMMENT)" +
                    "VALUES (NEXTVAL('order_seq'), #{orderStatusId}, #{customerName}, #{customerPhone}, #{customerComment});";
    String updateId = "UPDATE \"order\" " +
                      "SET ORDER_STATUS_ID = #{orderStatusId}, CUSTOMER_NAME = #{customerName}, " +
                      "CUSTOMER_PHONE = #{customerPhone}, CUSTOMER_COMMENT = #{customerComment} " +
                      "WHERE ID = #{id}";
    String deleteId = "DELETE FROM \"order\" WHERE ID = #{id}";

    @Insert(insert)
    void createOrder(int orderStatusId, String customerName, String customerPhone, String customerComment);

    Order getOrder(int id);

    @Update(updateId)
    void updateOrder(int id, int orderStatusId, String customerName, String customerPhone, String customerComment);

    @Delete(deleteId)
    void deleteOrder(long id);
}
