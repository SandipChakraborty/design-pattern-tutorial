package com.design.pattern.facade;

public class Main {
    public static void main(String[] args) {

        int accountNumber = 90876543;
        int securityCode = 9999;
        double depositAmount = 20_000;
        double withdrawAmount = 10_000;

        // With Facade Pattern
        System.out.println("\nWith Facade Pattern\n");
        BankFacade bank = new BankFacade(accountNumber, securityCode);

        bank.depositCash(depositAmount);
        bank.withdrawCash(withdrawAmount);

        // --------------

        // Without Facade Pattern
        System.out.println("\n-------------------------------------------------\n");
        System.out.println("Without Facade Pattern\n");
        AccountNumberCheck accCheck = new AccountNumberCheck();
        SecurityCodeCheck codeCheck = new SecurityCodeCheck();
        FundsCheck fundCheck = new FundsCheck();

        // Deposit cash
        if (accCheck.isValidAccount(accountNumber) &&
                codeCheck.isValidCode(securityCode)) {
            fundCheck.deposit(depositAmount);
        } else {
            System.out.println("Invalid account details");
        }

        // Withdraw cash
        if (accCheck.isValidAccount(accountNumber) &&
                codeCheck.isValidCode(securityCode)) {

            fundCheck.withdraw(withdrawAmount);
        } else {
            System.out.println("Invalid account details");
        }
        // --------------
    }
}