package com.design.pattern.builder.controller;

import com.design.pattern.builder.model.Order;
import com.design.pattern.builder.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/builder")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("")
    public ResponseEntity<Order> order(@RequestParam String orderId, @RequestParam String customerName,
                                       @RequestParam(required = false) Double discount, @RequestParam(required = false) Boolean giftWrap,
                                       @RequestParam(required = false) String deliveryInstructions, @RequestParam(required = false) Boolean priorityShipping,
                                       @RequestParam(required = false) String couponCode) {
        try {
            return ResponseEntity.ok(orderService.createOrder(orderId, customerName, discount, giftWrap, deliveryInstructions, priorityShipping, couponCode));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
