package com.github.RuSichPT.TestOrderMicroservice.controllers;

import com.github.RuSichPT.TestOrderMicroservice.entities.Order;
import com.github.RuSichPT.TestOrderMicroservice.services.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(
        value = "order",
        produces="application/json"
)
public class OrderController {

    @Autowired
    private OrderServiceImpl orderService;

    @PostMapping
    public Order insertOrder(@RequestBody Order order)
    {
        if (order.getPatient() == null)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return orderService.insert(order);
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
    public Order updateOrder(@PathVariable int id, @RequestBody Order order)
    {
        if (order.getPatient() == null)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        return orderService.update(id, order);
    }

    @DeleteMapping(path = "{id}")
    public void deleteOrder(@PathVariable int id)
    {
        orderService.delete(id);
    }
}
