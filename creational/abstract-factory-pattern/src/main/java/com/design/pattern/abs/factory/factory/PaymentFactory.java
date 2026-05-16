package com.design.pattern.abs.factory.factory;

import com.design.pattern.abs.factory.processor.PaymentProcessor;
import com.design.pattern.abs.factory.processor.RefundProcessor;

public interface PaymentFactory {
    PaymentProcessor createPaymentProcessor();

    RefundProcessor createRefundProcessor();
}
