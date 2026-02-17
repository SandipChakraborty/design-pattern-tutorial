package com.design.pattern.factory.processor;

import org.springframework.stereotype.Component;

@Component
public class PayPalPaymentProcessor implements PaymentProcessor {
    @Override
    public String processPayment(double amount) {
        return "Processed PayPal payment of ₹" + amount;
    }
}
