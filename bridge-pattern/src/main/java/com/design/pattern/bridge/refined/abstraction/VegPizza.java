package com.design.pattern.bridge.refined.abstraction;

import com.design.pattern.bridge.implementor.PizzaMaker;
import com.design.pattern.bridge.abstraction.Pizza;

import static com.design.pattern.bridge.util.Constants.GREEN;
import static com.design.pattern.bridge.util.Constants.RESET;

public class VegPizza extends Pizza {

    public VegPizza(PizzaMaker pizzaMaker) {
        super(pizzaMaker);
    }

    public void make() {
        pizzaMaker.preparePizza(GREEN + "Veg Pizza" + RESET);
    }
}
