package com.design.pattern.bridge.refined.abstraction;

import com.design.pattern.bridge.implementor.PizzaMaker;
import com.design.pattern.bridge.abstraction.Pizza;

import static com.design.pattern.bridge.util.Constants.RESET;
import static com.design.pattern.bridge.util.Constants.YELLOW;

public class CheesePizza extends Pizza {

    public CheesePizza(PizzaMaker pizzaMaker) {
        super(pizzaMaker);
    }

    public void make() {
        pizzaMaker.preparePizza(YELLOW + "Cheese Pizza" + RESET);
    }
}
