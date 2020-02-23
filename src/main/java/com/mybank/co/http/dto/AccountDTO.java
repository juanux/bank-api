package com.mybank.co.http.dto;

import java.util.Objects;

/**
 * Class that represent the transfer object for an account
 */
public class AccountDTO implements DTO{

    private String accountNumber;
    private Double balance;
    private String currency;

    public AccountDTO(){}

    public AccountDTO(String accountNumber, Double balance, String currency) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.currency = currency;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AccountDTO)) return false;
        AccountDTO that = (AccountDTO) o;
        return Objects.equals(getAccountNumber(), that.getAccountNumber()) &&
                Objects.equals(getBalance(), that.getBalance()) &&
                Objects.equals(getCurrency(), that.getCurrency());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAccountNumber(), getBalance(), getCurrency());
    }
}
