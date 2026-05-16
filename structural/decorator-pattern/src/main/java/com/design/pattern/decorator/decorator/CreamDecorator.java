package com.design.pattern.decorator.decorator;

import com.design.pattern.decorator.component.Coffee;

// Concrete Decorators
public class CreamDecorator extends CoffeeDecorator {
    public CreamDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return coffee.getDescription() + ", Cream";
    }

    @Override
    public double getCost() {
        return coffee.getCost() + 20.0;
    }
}
