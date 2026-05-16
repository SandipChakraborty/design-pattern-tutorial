package com.design.pattern.chain.of.responsibility;

public class Main {
    public static void main(String[] args) {

        LeaveApprover teamLead = new TeamLead();
        LeaveApprover manager = new Manager();
        LeaveApprover director = new Director();

        // Build Chain
        teamLead.setNextApprover(manager).setNextApprover(director);

        // Requests
        teamLead.approve(new LeaveRequest("John", 2));
        teamLead.approve(new LeaveRequest("Alice", 5));
        teamLead.approve(new LeaveRequest("Bob", 10));
        teamLead.approve(new LeaveRequest("David", 20));
    }
}