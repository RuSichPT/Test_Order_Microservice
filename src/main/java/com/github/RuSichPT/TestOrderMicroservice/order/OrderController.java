package com.github.RuSichPT.TestOrderMicroservice.order;

import org.springframework.web.bind.annotation.*;

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

    @PutMapping(path = "{id}")
    public void updateOrder(@PathVariable int id, @RequestBody Order order)
    {
        orderService.updateOrder(id, order);
    }

    @DeleteMapping(path = "{id}")
    public void deleteOrder(@PathVariable int id)
    {
        orderService.deleteOrder(id);
    }
}
