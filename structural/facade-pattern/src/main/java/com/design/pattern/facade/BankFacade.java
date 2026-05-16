package com.design.pattern.facade;

// Facade Class
class BankFacade {
    private final int accountNumber;
    private final int securityCode;

    private final AccountNumberCheck accCheck;
    private final SecurityCodeCheck codeCheck;
    private final FundsCheck fundCheck;

    public BankFacade(int accNo, int code) {
        this.accountNumber = accNo;
        this.securityCode = code;

        accCheck = new AccountNumberCheck();
        codeCheck = new SecurityCodeCheck();
        fundCheck = new FundsCheck();
    }

    public void depositCash(double amount) {
        if (accCheck.isValidAccount(accountNumber) &&
                codeCheck.isValidCode(securityCode)) {

            fundCheck.deposit(amount);
        } else {
            System.out.println("Invalid account details");
        }
    }

    public void withdrawCash(double amount) {
        if (accCheck.isValidAccount(accountNumber) &&
                codeCheck.isValidCode(securityCode)) {

            fundCheck.withdraw(amount);
        } else {
            System.out.println("Invalid account details");
        }
    }
}
