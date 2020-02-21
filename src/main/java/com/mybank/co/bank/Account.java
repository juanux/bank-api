package com.mybank.co.bank;

public class Account {
    private String accountNumber;
    private User user;
    private Long balance;
    private ECurrency currency;

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

enum ECurrency{
    DOLLAR,
    EURO,
    JPY,
    GBP
}
