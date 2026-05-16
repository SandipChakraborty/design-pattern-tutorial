package com.design.pattern.chain.of.responsibility;

// Concrete Handler
// Director
public class Director extends LeaveApprover {

    @Override
    public void approve(LeaveRequest request) {

        if (request.numberOfDays <= 15) {
            System.out.println("Director approve request for " + request.employeeName);
            return;
        }

        System.out.println(
                "Leave request rejected for "
                        + request.employeeName
        );
    }
}
