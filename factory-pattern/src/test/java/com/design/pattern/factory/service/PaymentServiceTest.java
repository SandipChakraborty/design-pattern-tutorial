package com.design.pattern.factory.service;

import com.design.pattern.factory.factory.PaymentProcessorFactory;
import com.design.pattern.factory.processor.PaymentProcessor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentServiceTest {
    @Mock
    private PaymentProcessorFactory factory;

    @Mock
    private PaymentProcessor processor;

    @InjectMocks
    private PaymentService service;

    @Test
    void shouldProcessPaymentSuccessfully() {

        when(factory.getProcessor("creditcard")).thenReturn(processor);
        when(processor.processPayment(100.0))
                .thenReturn("Processed Credit Card payment of $100.0");

        String result = service.process("creditcard", 100.0);

        assertEquals("Processed Credit Card payment of $100.0", result);

        verify(factory, times(1)).getProcessor("creditcard");
        verify(processor, times(1)).processPayment(100.0);
    }
}