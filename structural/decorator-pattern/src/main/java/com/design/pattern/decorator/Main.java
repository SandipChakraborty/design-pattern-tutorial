package com.design.pattern.decorator;


import com.design.pattern.decorator.component.Coffee;
import com.design.pattern.decorator.component.PlainCoffee;
import com.design.pattern.decorator.decorator.CreamDecorator;
import com.design.pattern.decorator.decorator.MilkDecorator;
import com.design.pattern.decorator.decorator.SugarDecorator;

public class Main {
    public static void main(String[] args) {
        Coffee coffee = new PlainCoffee();

        // Add Milk
        coffee = new MilkDecorator(coffee);

        // Add Sugar
        coffee = new SugarDecorator(coffee);

        // Add Cream
        coffee = new CreamDecorator(coffee);

        System.out.println("Description: " + coffee.getDescription());
        System.out.println("Cost: ₹" + coffee.getCost());


        // Another way
        // Add cream and Milk to the plain coffee
        Coffee plainCoffee = new CreamDecorator(new MilkDecorator(new PlainCoffee()));

        System.out.println("Description: " + plainCoffee.getDescription());
        System.out.println("Cost: ₹" + plainCoffee.getCost());

        // Only Plain Coffee
        Coffee espresso = new PlainCoffee();

        System.out.println("Description: " + espresso.getDescription());
        System.out.println("Cost: ₹" + espresso.getCost());
    }
}