package com.design.pattern.composite.leaf;

import com.design.pattern.composite.component.Employee;

public class Manager implements Employee {
    private final String name;
    private final String position;

    public Manager(String name, String position) {
        this.name = name;
        this.position = position;
    }

    @Override
    public void showEmployeeDetails() {
        System.out.println(name + " works as " + position);
    }
}
