package com.design.pattern.abs.factory.model;

import lombok.*;

@Data
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDetails {
    private String providerId;
    private double amount;
}
