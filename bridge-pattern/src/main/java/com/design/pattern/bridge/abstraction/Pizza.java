package com.design.pattern.bridge.abstraction;

import com.design.pattern.bridge.implementor.PizzaMaker;

public abstract class Pizza {
    protected PizzaMaker pizzaMaker;

    public Pizza(PizzaMaker pizzaMaker) {
        this.pizzaMaker = pizzaMaker;
    }

    public abstract void make();
}
