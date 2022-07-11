package com.github.RuSichPT.TestOrderMicroservice.order;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface OrderMapper {
    void createOrder(Order order);

    Order getOrder(int id);

    void updateOrder(Order order);

    void deleteOrder(int id);
}
