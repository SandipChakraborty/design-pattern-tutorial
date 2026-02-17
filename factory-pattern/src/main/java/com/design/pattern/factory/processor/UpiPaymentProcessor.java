package com.design.pattern.factory.processor;

import org.springframework.stereotype.Component;

@Component
public class UpiPaymentProcessor implements PaymentProcessor {
    @Override
    public String processPayment(double amount) {
        return "Processed UPI payment of ₹" + amount;
    }
}
