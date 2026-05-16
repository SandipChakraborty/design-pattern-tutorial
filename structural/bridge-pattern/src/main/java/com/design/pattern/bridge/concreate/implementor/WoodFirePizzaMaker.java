package com.design.pattern.bridge.concreate.implementor;

import com.design.pattern.bridge.implementor.PizzaMaker;

public class WoodFirePizzaMaker implements PizzaMaker {
    @Override
    public void preparePizza(String pizzaType) {
        System.out.println("Preparing " + pizzaType + " in Wood Fire Oven");
    }
}
