package com.design.pattern.decorator.component;

// Concrete Component
public class PlainCoffee implements Coffee {

    @Override
    public String getDescription() {
        return "Plain Coffee";
    }

    @Override
    public double getCost() {
        return 50.0;
    }
}
