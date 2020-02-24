package com.mybank.co.bank.service;

import com.mybank.co.bank.Account;
import com.mybank.co.http.dto.UserDTO;

import java.util.Optional;

/**
 * service with business logic for an Account
 */
public interface IAccountService {

    /**
     * Create a new aCCOUNT
     * @param userDTO
     * @return
     * @throws Exception
     */
    Account createAccount(UserDTO userDTO) throws Exception;

    /**
     * Get an Account for an specific id
     * @param userId
     * @return
     */
    Optional<Account> getByUserId(String userId);

    /**
     * Get an account for a given account id
     * @param accountId
     * @return
     */
    Optional<Account> getByAccountId(String accountId);

    /**
     * Delete an account for an specific id
     * @param accountId
     * @return
     */
    Account deleteAccount(String accountId);

    /**
     * transfer money fron one account to other account
     * @param origin
     * @param target
     * @param amount
     * @return
     */
    Double transfer(Account origin, Account target, Double amount);

}