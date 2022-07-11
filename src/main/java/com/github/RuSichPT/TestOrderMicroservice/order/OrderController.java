package com.github.RuSichPT.TestOrderMicroservice.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public void createOrder(@RequestBody Order order)
    {
        orderService.createOrder(order);
    }

    @GetMapping(path = "{id}")
    public Order getOrder(@PathVariable int id)
    {
        Order order = orderService.getOrder(id);

        if (order == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return order;
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
