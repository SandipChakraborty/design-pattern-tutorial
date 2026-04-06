package com.design.pattern.facade;

public class AccountNumberCheck {
    public boolean isValidAccount(int accNo) {
        // Let's say if the number length is 10 then the account is valid
        // Sample 1234567890
        return (accNo + "").length() == 8;
    }
}
