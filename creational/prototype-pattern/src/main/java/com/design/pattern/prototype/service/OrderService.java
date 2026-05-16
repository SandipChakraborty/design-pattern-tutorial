package com.design.pattern.prototype.service;

import com.design.pattern.prototype.Order;
import com.design.pattern.prototype.OrderPrototypeRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderPrototypeRegistry registry;

    public Order createOrder(String type, String extraItem) {
        Order order = registry.getClone(type);

        // customize cloned object
        order.getItems().add(extraItem);

        return order;
    }
}
