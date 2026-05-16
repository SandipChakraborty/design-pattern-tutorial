package com.design.pattern.abs.factory.processor;

import org.springframework.stereotype.Component;

@Component
public class PaypalRefundProcessor implements RefundProcessor {
    @Override
    public String refund(double amount) {
        return "PayPal Refund done for amount : " + amount;
    }
}
