package com.design.pattern.abs.factory.service;

import com.design.pattern.abs.factory.factory.PaymentFactory;
import com.design.pattern.abs.factory.factory.PaymentFactoryProvider;
import com.design.pattern.abs.factory.model.PaymentDetails;
import com.design.pattern.abs.factory.model.PaymentProvider;
import com.design.pattern.abs.factory.processor.PaymentProcessor;
import org.springframework.stereotype.Service;

@Service
public class CheckoutService {
    private final PaymentFactoryProvider factoryProvider;

    public CheckoutService(PaymentFactoryProvider factoryProvider) {
        this.factoryProvider = factoryProvider;
    }

    public String checkout(PaymentDetails paymentDetails) {

        // 1️⃣ Get provider from external API
        PaymentProvider provider = PaymentProvider.valueOf(paymentDetails.getProviderId());

        // 2️⃣ Get correct factory
        PaymentFactory factory = factoryProvider.getFactory(provider);

        // 3️⃣ Create processor
        PaymentProcessor processor = factory.createPaymentProcessor();

        // 4️⃣ Execute payment
        return processor.pay(paymentDetails.getAmount());
    }
}
