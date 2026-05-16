package com.design.pattern.chain.of.responsibility;

// Abstract Handler
public abstract class LeaveApprover {

    protected LeaveApprover nextApprover;

    public LeaveApprover setNextApprover(LeaveApprover nextApprover) {
        this.nextApprover = nextApprover;
        return this;
    }

    public abstract void approve(LeaveRequest request);
}
