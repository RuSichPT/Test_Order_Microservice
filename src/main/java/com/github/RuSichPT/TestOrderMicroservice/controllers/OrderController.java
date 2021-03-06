package com.github.RuSichPT.TestOrderMicroservice.controllers;

import com.github.RuSichPT.TestOrderMicroservice.order.Order;
import com.github.RuSichPT.TestOrderMicroservice.services.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderServiceImpl orderService;

    @PostMapping
    public void insertOrder(@RequestBody Order order)
    {
        orderService.insert(order);
    }

    @GetMapping(path = "{id}")
    public Order selectOrder(@PathVariable int id)
    {
        Order order = orderService.select(id);

        if (order == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return order;
    }

    @PutMapping(path = "{id}")
    public void updateOrder(@PathVariable int id, @RequestBody Order order)
    {
        orderService.update(id, order);
    }

    @DeleteMapping(path = "{id}")
    public void deleteOrder(@PathVariable int id)
    {
        orderService.delete(id);
    }
}
