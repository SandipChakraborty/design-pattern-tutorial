package com.design.pattern.bridge.refined.abstraction;

import com.design.pattern.bridge.abstraction.Pizza;
import com.design.pattern.bridge.implementor.PizzaMaker;

import static com.design.pattern.bridge.util.Constants.RED;
import static com.design.pattern.bridge.util.Constants.RESET;

public class ChickenPizza extends Pizza {
    public ChickenPizza(PizzaMaker pizzaMaker) {
        super(pizzaMaker);
    }

    @Override
    public void make() {
        pizzaMaker.preparePizza(RED + "Chicken Pizza" + RESET);
    }
}
