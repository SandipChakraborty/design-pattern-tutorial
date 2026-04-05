package com.design.pattern.composite.composite;

import com.design.pattern.composite.component.Employee;

import java.util.ArrayList;
import java.util.List;

public class CompanyDirectory implements Employee {
    private final List<Employee> employees = new ArrayList<>();

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public void removeEmployee(Employee employee) {
        employees.remove(employee);
    }

    @Override
    public void showEmployeeDetails() {
        for (Employee employee : employees) {
            employee.showEmployeeDetails();
        }
    }
}
