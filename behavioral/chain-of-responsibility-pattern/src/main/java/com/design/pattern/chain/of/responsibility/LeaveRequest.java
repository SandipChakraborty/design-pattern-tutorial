package com.design.pattern.chain.of.responsibility;

public class LeaveRequest {
    String employeeName;
    int numberOfDays;
    public LeaveRequest(String employeeName, int numberOfDays) {
        this.employeeName = employeeName;
        this.numberOfDays = numberOfDays;
    }
}
