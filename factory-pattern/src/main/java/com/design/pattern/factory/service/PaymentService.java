package com.design.pattern.factory.service;

import com.design.pattern.factory.factory.PaymentProcessorFactory;
import com.design.pattern.factory.processor.PaymentProcessor;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    private final Counter paymentCounter;
    private final Timer paymentTimer;
    private final PaymentProcessorFactory factory;

    @Autowired
    public PaymentService(MeterRegistry registry, PaymentProcessorFactory factory) {
        this.paymentCounter = registry.counter("payments_total");
        this.paymentTimer = registry.timer("payment_duration");
        this.factory = factory;
    }

    public String process(String type, double amount) {
        paymentCounter.increment();
        return paymentTimer.record(() -> {
            PaymentProcessor processor = factory.getProcessor(type);
            return processor.processPayment(amount);
        });
    }
}
