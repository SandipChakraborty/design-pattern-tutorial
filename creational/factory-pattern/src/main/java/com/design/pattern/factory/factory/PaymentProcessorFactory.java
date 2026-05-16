package com.design.pattern.factory.factory;

import com.design.pattern.factory.exception.FactoryException;
import com.design.pattern.factory.processor.PaymentProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class PaymentProcessorFactory {
    private final Map<String, PaymentProcessor> processors;

    @Autowired
    public PaymentProcessorFactory(List<PaymentProcessor> processorList) {
        processors = processorList.stream()
                .collect(Collectors.toMap(
                        processor -> processor.getClass().getSimpleName().toLowerCase(),
                        processor -> processor
                ));
    }

    public PaymentProcessor getProcessor(String type) {
        return switch (type.toLowerCase()) {
            case "creditcard" -> processors.get("creditcardpaymentprocessor");
            case "debitcard" -> processors.get("debitcardpaymentprocessor");
            case "paypal" -> processors.get("paypalpaymentprocessor");
            case "upi" -> processors.get("upipaymentprocessor");
            default -> throw new FactoryException("Invalid payment type");
        };
    }
}
