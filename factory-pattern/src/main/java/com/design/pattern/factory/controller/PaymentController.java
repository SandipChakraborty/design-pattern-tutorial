package com.design.pattern.factory.controller;

import com.design.pattern.factory.exception.FactoryException;
import com.design.pattern.factory.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/factory")
public class PaymentController {

    private final PaymentService service;

    @Autowired
    public PaymentController(PaymentService service) {
        this.service = service;
    }

    @PostMapping("/payments/{type}")
    public ResponseEntity<String> makePayment(
            @PathVariable String type,
            @RequestParam double amount) {

        try {
            return ResponseEntity.ok(service.process(type, amount));
        } catch (FactoryException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
