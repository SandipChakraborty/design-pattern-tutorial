package com.design.pattern.chain.of.responsibility;

// Concrete Handler
// Manager
public class Manager extends LeaveApprover{

    @Override
    public void approve(LeaveRequest request) {

        if (request.numberOfDays <= 7) {
            System.out.println("Manager Approved leaves for " + request.employeeName);
            return;
        }

        if (nextApprover != null) {
            nextApprover.approve(request);
        }
    }
}
