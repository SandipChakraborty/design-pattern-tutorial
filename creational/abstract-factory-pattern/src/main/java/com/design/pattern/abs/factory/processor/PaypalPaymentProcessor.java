package com.design.pattern.abs.factory.processor;

import org.springframework.stereotype.Component;

@Component
public class PaypalPaymentProcessor implements PaymentProcessor {
    @Override
    public String pay(double amount) {
        return "PayPal Payment done for amount : " + amount;
    }
}
