package com.design.pattern.bridge.concreate.implementor;

import com.design.pattern.bridge.implementor.PizzaMaker;

public class OvenPizzaMaker implements PizzaMaker {
    @Override
    public void preparePizza(String pizzaType) {
        System.out.println("Preparing " + pizzaType + " in Electric Oven");
    }
}
