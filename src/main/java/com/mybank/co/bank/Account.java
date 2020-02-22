package com.mybank.co.bank;

import java.util.function.Function;

/**
 * This class represent an account for an specific user in a specific currency
 */
public class Account  {
    private String accountNumber;
    private User user;
    private Double balance;
    private ECurrency currency;

    /**
     * Default constructor for a Account
     * @param accountNumber
     * @param user
     * @param balance
     * @param currency
     */
    public Account(String accountNumber, User user, Double balance, ECurrency currency) {
        this.accountNumber = accountNumber;
        this.user = user;
        this.balance = balance;
        this.currency = currency;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public User getUser() {
        return user;
    }

    public Double getBalance() {
        return balance;
    }

    public ECurrency getCurrency() {
        return currency;
    }

}

