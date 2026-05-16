package com.design.pattern.abs.factory.controller;

import com.design.pattern.abs.factory.model.PaymentDetails;
import com.design.pattern.abs.factory.service.CheckoutService;
import com.design.pattern.abs.factory.service.RefundService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/abs/factory")
@RequiredArgsConstructor
public class CheckoutController {

    private final CheckoutService checkoutService;
    private final RefundService refundService;

    @PostMapping("/pay")
    public ResponseEntity<String> pay(@RequestBody PaymentDetails paymentDetails) {
        try {
            return new ResponseEntity<>(checkoutService.checkout(paymentDetails), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/refund")
    public ResponseEntity<String> refund(@RequestBody PaymentDetails paymentDetails) {
        try {
            return new ResponseEntity<>(refundService.refund(paymentDetails), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
