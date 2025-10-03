package com.design.pattern.order.controller;

import com.design.pattern.order.model.Order;
import com.design.pattern.order.repo.OrderRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderRepository repo;

    public OrderController(OrderRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Order> all() {
        return repo.findAll();
    }

    @PostMapping
    public Order create(@RequestBody Order order) {
        return repo.save(order);
    }
}
