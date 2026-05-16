package com.design.pattern.builder.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@EqualsAndHashCode(callSuper = false)
public class Order implements Serializable {
    private final String orderId;
    private final String customerName;
    private final Double discount;
    private final Boolean giftWrap;
    private final String deliveryInstructions;
    private final Boolean priorityShipping;
    private final String couponCode;

    private Order(OrderBuilder builder) {
        this.orderId = builder.orderId;
        this.customerName = builder.customerName;
        this.discount = builder.discount;
        this.giftWrap = builder.giftWrap;
        this.deliveryInstructions = builder.deliveryInstructions;
        this.priorityShipping = builder.priorityShipping;
        this.couponCode = builder.couponCode;
    }

    public static class OrderBuilder {

        // Required fields
        private final String orderId;
        private final String customerName;

        // Optional fields
        private Double discount;
        private Boolean giftWrap;
        private String deliveryInstructions;
        private Boolean priorityShipping;
        private String couponCode;

        public OrderBuilder(String orderId, String customerName) {
            this.orderId = orderId;
            this.customerName = customerName;
        }

        public OrderBuilder discount(Double discount) {
            this.discount = discount;
            return this;
        }

        public OrderBuilder giftWrap(Boolean giftWrap) {
            this.giftWrap = giftWrap;
            return this;
        }

        public OrderBuilder deliveryInstructions(String instructions) {
            this.deliveryInstructions = instructions;
            return this;
        }

        public OrderBuilder priorityShipping(Boolean priority) {
            this.priorityShipping = priority;
            return this;
        }

        public OrderBuilder couponCode(String couponCode) {
            this.couponCode = couponCode;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }
}
