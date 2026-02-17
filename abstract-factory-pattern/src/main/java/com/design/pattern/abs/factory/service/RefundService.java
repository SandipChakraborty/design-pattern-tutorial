package com.design.pattern.abs.factory.service;

import com.design.pattern.abs.factory.factory.PaymentFactory;
import com.design.pattern.abs.factory.factory.PaymentFactoryProvider;
import com.design.pattern.abs.factory.model.PaymentDetails;
import com.design.pattern.abs.factory.model.PaymentProvider;
import com.design.pattern.abs.factory.processor.RefundProcessor;
import org.springframework.stereotype.Service;

@Service
public class RefundService {
    private final PaymentFactoryProvider factoryProvider;

    public RefundService(PaymentFactoryProvider factoryProvider) {
        this.factoryProvider = factoryProvider;
    }

    public String refund(PaymentDetails paymentDetails) {

        // 1️⃣ Get provider dynamically
        PaymentProvider provider = PaymentProvider.valueOf(paymentDetails.getProviderId());

        // 2️⃣ Get correct factory
        PaymentFactory factory = factoryProvider.getFactory(provider);

        // 3️⃣ Create RefundProcessor
        RefundProcessor refundProcessor = factory.createRefundProcessor();

        // 4️⃣ Execute refund
        return refundProcessor.refund(paymentDetails.getAmount());
    }
}
