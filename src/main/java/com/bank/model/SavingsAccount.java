package com.bank.model;

public class SavingsAccount extends Account {

    private double interestRate;

    public SavingsAccount(String accountNumber, String customerName,
                          String email, String password,
                          double balance, double interestRate) {
        super(accountNumber, customerName, email, password, balance);
        this.interestRate = interestRate;
    }

    public double calculateInterest() {
        return getBalance() * interestRate / 100;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }
}
