package com.design.pattern.factory.processor;

import org.springframework.stereotype.Component;

@Component
public class DebitCardPaymentProcessor implements PaymentProcessor {
    @Override
    public String processPayment(double amount) {
        return "Processed Debit Card payment of ₹" + amount;
    }
}
