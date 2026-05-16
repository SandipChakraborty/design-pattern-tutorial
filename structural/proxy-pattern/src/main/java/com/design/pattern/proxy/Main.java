package com.design.pattern.proxy;

public class Main {
    public static void main(String[] args) {
        Image image = new ProxyImage("photo.jpg");

        // Image will be loaded only when needed
        image.display(); // loads + displays
        System.out.println();
        image.display(); // only displays (no loading again)
    }
}