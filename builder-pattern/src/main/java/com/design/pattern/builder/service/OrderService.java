package com.design.pattern.builder.service;

import com.design.pattern.builder.model.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    public Order createOrder(String orderId, String customerName,
                             Double discount, Boolean giftWrap,
                             String deliveryInstructions, Boolean priorityShipping,
                             String couponCode) {

        return new Order.OrderBuilder(orderId, customerName)
                .discount(discount)
                .giftWrap(giftWrap)
                .deliveryInstructions(deliveryInstructions)
                .priorityShipping(priorityShipping)
                .couponCode(couponCode)
                .build();
    }
}
