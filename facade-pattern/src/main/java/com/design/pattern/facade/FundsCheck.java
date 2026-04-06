package com.design.pattern.facade;

import lombok.Getter;

@Getter
public class FundsCheck {
    private double balance = 1000.0;

    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited: " + amount);
    }

    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println("Withdrawn: " + amount);
        } else {
            System.out.println("Insufficient funds");
        }
    }
}
