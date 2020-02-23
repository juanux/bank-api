package com.mybank.co.bank.service;

import com.mybank.co.bank.Account;
import com.mybank.co.http.dto.UserDTO;

import java.util.Optional;

public interface IAccountService {

    Account createAccount(UserDTO userDTO) throws Exception;
    Optional<Account> getByUserId(String userId);
    Optional<Account> getByAccountId(String accountId);
    Account deleteAccount(String accountId);
    Double transfer(Account origin, Account target, Double amount);

}