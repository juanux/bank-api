package com.mybank.co.http.dto;

/**
 * Class that represent the transfer object for an account
 */
public class AccountDTO implements DTO{

    private String accountNumber;
    private UserDTO user;
    private Long balance;
    private String currency;

    public AccountDTO(){}

    public AccountDTO(String accountNumber, UserDTO user, Long balance, String currency) {
        this.accountNumber = accountNumber;
        this.user = user;
        this.balance = balance;
        this.currency = currency;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
