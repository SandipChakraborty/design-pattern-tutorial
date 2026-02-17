package com.design.pattern.abs.factory.processor;

import org.springframework.stereotype.Component;

@Component
public class StripePaymentProcessor implements PaymentProcessor {

    @Override
    public String pay(double amount) {
        return "Stripe Payment done for amount : " + amount;
    }
}
