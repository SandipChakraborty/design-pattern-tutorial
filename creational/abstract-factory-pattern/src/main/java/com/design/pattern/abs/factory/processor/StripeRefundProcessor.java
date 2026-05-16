package com.design.pattern.abs.factory.processor;

import org.springframework.stereotype.Component;

@Component
public class StripeRefundProcessor implements RefundProcessor {
    @Override
    public String refund(double amount) {
        return "Stripe Refund done for amount : " + amount;
    }
}
