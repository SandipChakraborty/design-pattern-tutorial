package com.design.pattern.factory.processor;

import org.springframework.stereotype.Component;

@Component
public class CreditCardPaymentProcessor implements PaymentProcessor {
    @Override
    public String processPayment(double amount) {
        return "Processed Credit Card payment of ₹" + amount;
    }
}
