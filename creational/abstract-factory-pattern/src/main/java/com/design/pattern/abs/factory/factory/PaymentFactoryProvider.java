package com.design.pattern.abs.factory.factory;

import com.design.pattern.abs.factory.model.PaymentProvider;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class PaymentFactoryProvider {

    private final Map<String, PaymentFactory> factoryMap;

    public PaymentFactoryProvider(Map<String, PaymentFactory> factoryMap) {
        this.factoryMap = factoryMap;
    }

    public PaymentFactory getFactory(PaymentProvider provider) {

        PaymentFactory factory = factoryMap.get(provider.name());

        if (factory == null) {
            throw new IllegalArgumentException("Unsupported provider: " + provider);
        }

        return factory;
    }
}
