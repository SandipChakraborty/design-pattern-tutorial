package com.design.pattern.bridge;


import com.design.pattern.bridge.abstraction.Pizza;
import com.design.pattern.bridge.concreate.implementor.AirFryerPizzaMaker;
import com.design.pattern.bridge.concreate.implementor.OvenPizzaMaker;
import com.design.pattern.bridge.concreate.implementor.WoodFirePizzaMaker;
import com.design.pattern.bridge.refined.abstraction.CheesePizza;
import com.design.pattern.bridge.refined.abstraction.ChickenPizza;
import com.design.pattern.bridge.refined.abstraction.VegPizza;

public class Main {
    public static void main(String[] args) {
        Pizza vegOvenPizza = new VegPizza(new OvenPizzaMaker());
        Pizza cheeseWoodPizza = new CheesePizza(new WoodFirePizzaMaker());
        Pizza chickenPizza = new ChickenPizza(new AirFryerPizzaMaker());

        vegOvenPizza.make();
        cheeseWoodPizza.make();
        chickenPizza.make();
    }
}
