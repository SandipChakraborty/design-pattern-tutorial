package com.design.pattern.abs.factory.factory;

import com.design.pattern.abs.factory.processor.PaymentProcessor;
import com.design.pattern.abs.factory.processor.PaypalPaymentProcessor;
import com.design.pattern.abs.factory.processor.PaypalRefundProcessor;
import com.design.pattern.abs.factory.processor.RefundProcessor;
import org.springframework.stereotype.Component;

@Component("PAYPAL")
public class PaypalPaymentFactory implements PaymentFactory {

    private final PaypalPaymentProcessor paymentProcessor;
    private final PaypalRefundProcessor refundProcessor;

    public PaypalPaymentFactory(PaypalPaymentProcessor paymentProcessor, PaypalRefundProcessor refundProcessor) {
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
