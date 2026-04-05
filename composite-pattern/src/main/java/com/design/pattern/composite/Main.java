package com.design.pattern.composite;

import com.design.pattern.composite.component.Employee;
import com.design.pattern.composite.composite.CompanyDirectory;
import com.design.pattern.composite.leaf.Developer;
import com.design.pattern.composite.leaf.Manager;

public class Main {
    public static void main(String[] args) {
        Employee dev1 = new Developer("Alice", "Backend Developer");
        Employee dev2 = new Developer("Bob", "Frontend Developer");

        Employee manager1 = new Manager("Charlie", "Team Lead");

        CompanyDirectory engineeringTeam = new CompanyDirectory();
        engineeringTeam.addEmployee(dev1);
        engineeringTeam.addEmployee(dev2);

        CompanyDirectory company = new CompanyDirectory();
        company.addEmployee(manager1);
        company.addEmployee(engineeringTeam);

        company.showEmployeeDetails();
    }
}