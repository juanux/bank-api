package com.mybank.co.bank;

/**
 * This class represent an account for an specific user in a specific currency
 */
public class Account {
    private String accountNumber;
    private User user;
    private Long balance;
    private ECurrency currency;

    /**
     * Default constructor for a Account
     * @param accountNumber
     * @param user
     * @param balance
     * @param currency
     */
    public Account(String accountNumber, User user, Long balance, ECurrency currency) {
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

    public Long getBalance() {
        return balance;
    }

    public ECurrency getCurrency() {
        return currency;
    }
}

/**
 * This enum represents the different currencies supported
 */
enum ECurrency{
    DOLLAR,
    EURO,
    JPY,
    GBP
}
