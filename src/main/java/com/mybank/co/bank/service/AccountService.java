package com.mybank.co.bank.service;

import com.mybank.co.bank.Account;
import com.mybank.co.bank.User;

public interface AccountService {

    Account createAccount(User user,String accountNumber,Double initialBalance,String currency);
    Account deleteAccount(String accountId);
    Double updateBalance(String accountNumber,Double deltaValue);

}