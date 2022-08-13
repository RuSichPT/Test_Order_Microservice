package com.github.RuSichPT.TestOrderMicroservice.mappers;

import com.github.RuSichPT.TestOrderMicroservice.entities.Order;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface OrderMapper {
    void insertOrder(Order order);

    Order selectOrder(int id);

    void updateOrder(Order order);

    void deleteOrder(int id);
}
