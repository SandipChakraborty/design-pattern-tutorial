package com.design.pattern.flyweight;

// Concrete Flyweight
public class ConcreteLetter implements Letter {
    private final char symbol; // intrinsic (shared)

    public ConcreteLetter(char symbol) {
        this.symbol = symbol;
    }

    @Override
    public void display(int x, int y) {
        System.out.println("Letter: " + symbol + " at (" + x + "," + y + ")");
    }
}
