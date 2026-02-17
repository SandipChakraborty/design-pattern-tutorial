package com.design.pattern.abs.factory.factory;

import com.design.pattern.abs.factory.processor.PaymentProcessor;
import com.design.pattern.abs.factory.processor.RefundProcessor;
import com.design.pattern.abs.factory.processor.StripePaymentProcessor;
import com.design.pattern.abs.factory.processor.StripeRefundProcessor;
import org.springframework.stereotype.Component;

@Component("STRIPE")
public class StripePaymentFactory implements PaymentFactory {

    private final StripePaymentProcessor paymentProcessor;
    private final StripeRefundProcessor refundProcessor;

    public StripePaymentFactory(StripePaymentProcessor paymentProcessor, StripeRefundProcessor refundProcessor) {
        this.paymentProcessor = paymentProcessor;
        this.refundProcessor = refundProcessor;
    }

    @Override
    public PaymentProcessor createPaymentProcessor() {
        return paymentProcessor;
    }

    @Override
    public RefundProcessor createRefundProcessor() {
        return refundProcessor;
    }
}
