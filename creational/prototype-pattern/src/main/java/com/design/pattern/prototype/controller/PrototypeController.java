package com.design.pattern.prototype.controller;

import com.design.pattern.prototype.Order;
import com.design.pattern.prototype.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/prototype")
@RequiredArgsConstructor
public class PrototypeController {

    private final OrderService service;

    @PostMapping
    public Order createOrder(@RequestParam String type,
                             @RequestParam String item) {
        return service.createOrder(type, item);
    }
}
