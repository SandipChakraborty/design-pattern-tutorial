package com.design.pattern.prototype;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class OrderPrototypeRegistry {
    private static final String BASIC = "BASIC";
    private static final String PREMIUM = "PREMIUM";
    private static final String ITEM_1 = "item1";
    private static final String ITEM_2 = "item2";
    private static final String INVALID_ORDER_TYPE = "Invalid order type";
    private final Map<String, Order> prototypes = new HashMap<>();

    public OrderPrototypeRegistry() {
        prototypes.put(BASIC,
                Order.builder()
                        .orderType(BASIC)
                        .items(new ArrayList<>(List.of(ITEM_1)))
                        .priorityShipping(false)
                        .insurance(false)
                        .build()
        );

        prototypes.put(PREMIUM,
                Order.builder()
                        .orderType(PREMIUM)
                        .items(new ArrayList<>(List.of(ITEM_1, ITEM_2)))
                        .priorityShipping(true)
                        .insurance(true)
                        .build()
        );
    }

    public Order getClone(String type) {
        Order prototype = prototypes.get(type);
        if (prototype == null) {
            throw new IllegalArgumentException(INVALID_ORDER_TYPE);
        }
        return prototype.clone();
    }
}
