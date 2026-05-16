package com.design.pattern.chain.of.responsibility;

// Concrete Handler
// Team Lead
public class TeamLead extends LeaveApprover {

    @Override
    public void approve(LeaveRequest request) {

        if (request.numberOfDays <= 3) {
            System.out.println("Team Lead Approved leaves for " + request.employeeName);
            return;
        }

        if (nextApprover != null) {
            nextApprover.approve(request);
        }
    }
}
