package com.github.RuSichPT.TestOrderMicroservice.controllers;

import com.github.RuSichPT.TestOrderMicroservice.entities.Order;
import com.github.RuSichPT.TestOrderMicroservice.services.OrderServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderServiceImpl orderService;

    @PostMapping
    public Order insertOrder(@RequestBody Order order)
    {


        if (order.getPatient() == null)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        Order resultOrder = orderService.insert(order);

        LOGGER.info("insert order id" + resultOrder.getId());

        return resultOrder;
    }

    @GetMapping(path = "{id}")
    public Order selectOrder(@PathVariable int id)
    {
        Order order = orderService.select(id);

        if (order == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        LOGGER.info("select order id=" + id);

        return order;
    }

    @PutMapping(path = "{id}")
    public Order updateOrder(@PathVariable int id, @RequestBody Order order)
    {
        if (order.getPatient() == null)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        LOGGER.info("update order id=" + id);

        return orderService.update(id, order);
    }

    @DeleteMapping(path = "{id}")
    public void deleteOrder(@PathVariable int id)
    {
        orderService.delete(id);

        LOGGER.info("delete order id=" + id);
    }
}
