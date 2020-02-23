package com.mybank.co.bank.service.impl;

import com.mybank.co.bank.*;
import com.mybank.co.bank.repositories.IAccountRepository;
import com.mybank.co.bank.service.IAccountService;
import com.mybank.co.http.dto.UserDTO;

import java.util.Optional;
import java.util.UUID;

public class AccountServiceImpl implements IAccountService {

    private IAccountRepository repository;

    public AccountServiceImpl(IAccountRepository repository) throws Exception {
        this.repository = repository;
    }

    @Override
    public Account createAccount(UserDTO userDTO) throws Exception {

        Account account = fromDto(userDTO);

        Optional<Account> theAccountAlreadyExist= repository.getAccountById(account.getAccountNumber()).join();

        if(theAccountAlreadyExist.isPresent()){
            throw new IllegalArgumentException("The given account number already exist");
        }

        return repository.createNewAccount(account.getUser(), account.getAccountNumber(), account.getBalance(), account.getCurrency()).join();

    }

    @Override
    public Optional<Account> getByUserId(String userId) {

        return repository.getAccountByUserId(userId).join();

    }

    @Override
    public Optional<Account> getByAccountId(String accountId) {
        return repository.getAccountById(accountId).join();
    }

    @Override
    public Account deleteAccount(String accountId) {
        return null;
    }

    @Override
    public Double transfer(Account origin, Account target, Double amount) {
         return repository.updateAccountBalance(new Account(origin.getAccountNumber(), origin.getUser(), origin.getBalance() - amount, origin.getCurrency()))
                .thenCompose(a-> repository.updateAccountBalance(new Account(target.getAccountNumber(), target.getUser(), target.getBalance() + amount, target.getCurrency())))
        .join().getBalance();

    }




    private Account fromDto(UserDTO userDTO) throws IllegalArgumentException {

        System.out.println("Validating Account ");

        String userId = userDTO.getId() == null ? UUID.randomUUID().toString() : userDTO.getId();

        System.out.println("Validating Account 2");
        if(userDTO.getName() == null || userDTO.getName().isEmpty()){
            System.out.println("Validating Account name" );
            throw new IllegalArgumentException("User name can´t be empty");
        }
        if(userDTO.getLastName() == null || userDTO.getLastName().isEmpty()){
            System.out.println("Validating Account last name" );
            throw new IllegalArgumentException("User last name can´t be empty");
        }

        if(userDTO.getBirthDay() == null ){
            System.out.println("Validating Account birthday" );
            throw new IllegalArgumentException("User birthday can´t be empty");
        }

        if(userDTO.getDocumentId() == null || userDTO.getDocumentId().isEmpty()){
            System.out.println("Validating Account document id" );
            throw new IllegalArgumentException("User document id can´t be empty");
        }

        try{
            EDocumentType.valueOf(userDTO.getDocumentType());
        }catch(IllegalArgumentException e){
            System.out.println("Validating Account document type" );
            throw new IllegalArgumentException("Invalid document type");
        }

        try{
            EGender.valueOf(userDTO.getGender());
        }catch(IllegalArgumentException e){
            System.out.println("Validating Account gender" );
            throw new IllegalArgumentException("Invalid  gender");
        }

        try{
            ECurrency.valueOf(userDTO.getAccount().getCurrency());
        }catch(IllegalArgumentException e){
            System.out.println("Validating Account currency" );
            throw new IllegalArgumentException("Currency not supported");
        }

        if(userDTO.getAccount().getAccountNumber() == null || userDTO.getAccount().getAccountNumber().isEmpty()){
            System.out.println("Validating Account account numer" );
            throw new IllegalArgumentException("Account number must be provided");
        }

        if(userDTO.getAccount().getBalance() == null || userDTO.getAccount().getBalance() < 0){
            System.out.println("Validating Account balance" );
            throw new IllegalArgumentException("Balance must be higher tha 0");
        }

        User user =  new User(userId, userDTO.getName(), userDTO.getLastName(), EDocumentType.valueOf(userDTO.getDocumentType()),
                userDTO.getDocumentId(), userDTO.getEmail(), userDTO.getBirthDay(), EGender.valueOf(userDTO.getGender()));
        System.out.println("Account is valid" );
        return new Account(userDTO.getAccount().getAccountNumber(),  user, userDTO.getAccount().getBalance(), ECurrency.valueOf(userDTO.getAccount().getCurrency()));

    }

}
