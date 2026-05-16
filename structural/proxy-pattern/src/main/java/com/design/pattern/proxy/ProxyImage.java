package com.design.pattern.proxy;

// Proxy Class
public class ProxyImage implements Image {
    private RealImage realImage;
    private final String fileName;

    public ProxyImage(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void display() {
        // Lazy initialization
        if (realImage == null) {
            realImage = new RealImage(fileName);
        }
        realImage.display();
    }
}
