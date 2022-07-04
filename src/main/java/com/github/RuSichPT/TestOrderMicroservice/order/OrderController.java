package com.github.RuSichPT.TestOrderMicroservice.order;

import org.apache.ibatis.annotations.Delete;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService1) {
        this.orderService = orderService1;
    }

    @PostMapping
    public void createOrder(@RequestBody Order order)
    {
        orderService.createOrder(order);
    }

    @GetMapping(path = "{id}")
    public Order getOrder(@PathVariable int id)
    {
        return orderService.getOrder(id);
    }

    @DeleteMapping(path = "{id}")
    public void deleteOrder(@PathVariable long id)
    {
        orderService.deleteOrder(id);
    }
}
