package com.github.RuSichPT.TestOrderMicroservice.order;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService1) {
        this.orderService = orderService1;
    }

    @GetMapping(path = "all")
    public List<Order> getOrders()
    {
        return orderService.getOrders();
    }

    @GetMapping(path = "all1")
    public List<OrderItem> getOrderItems()
    {
        return orderService.getOrderItems();
    }

    @GetMapping(path = "test")
    public List<OrderItem> getOrderItemByOrderId()
    {
        return orderService.getOrderItemByOrderId(1000);
    }

    @GetMapping(path = "{id}")
    public Order getOrderById(@PathVariable long id)
    {
        return orderService.getOrderById(id);
    }



//    @PostMapping
//    public Order createOrder(@RequestBody Order order)
//    {
//        return orderService.createOrder(order);
//    }

    @GetMapping(path = "hello")
    public String hello()
    {
        return "hello";
    }
}
