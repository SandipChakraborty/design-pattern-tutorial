package com.design.pattern.prototype;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order implements Prototype<Order> {
    private String orderType;
    private List<String> items;
    private boolean priorityShipping;
    private boolean insurance;

    // Deep Copy Constructor
    public Order(Order target) {
        this.orderType = target.getOrderType();
        this.items = new ArrayList<>(target.getItems());
        this.priorityShipping = target.isPriorityShipping();
        this.insurance = target.isInsurance();
    }

    @Override
    public Order clone() {
        return new Order(this);
    }
}
