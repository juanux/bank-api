package com.mybank.co.bank.repositories.impl;

import com.mybank.co.bank.*;
import com.mybank.co.bank.repositories.IAccountRepository;
import com.mybank.co.dao.IAccountDAO;
import com.mybank.co.dao.IUserDAO;
import com.mybank.co.dao.jooq.tables.records.AccountRecord;
import com.mybank.co.dao.jooq.tables.records.UserRecord;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class AccountRepositoryImpl implements IAccountRepository {

    private IUserDAO userDAO;
    private IAccountDAO accountDAO;

    public AccountRepositoryImpl(IUserDAO userDAO, IAccountDAO accountDAO) {
        this.userDAO = userDAO;
        this.accountDAO = accountDAO;
    }

    @Override
    public CompletableFuture<Account> createNewAccount(User user, String accountNumber, Double initialBalance, ECurrency currency) {

        UserRecord userRecord = new UserRecord(
                user.getId(),
                user.getName(),
                user.getLastName(),
                user.getDocumentType().toString(),
                user.getDocumentId(), user.getEmail(),
                user.getBirthDay(),
                user.getGender().toString());
        AccountRecord accountRecord = new AccountRecord(accountNumber, user.getId(), initialBalance, currency.toString(), true);


        return userDAO.createUser(userRecord)
                .thenCompose(r -> accountDAO.createAccount(accountRecord))
                .thenCompose(a -> CompletableFuture.supplyAsync(() -> new Account(accountNumber, user, initialBalance, currency)));

    }

    @Override
    public CompletableFuture<Optional<Account>> getAccountById(String accountId) {

        return accountDAO.getAccountById(accountId)
                .thenComposeAsync((maybeAccountRecord) -> {
                    if (maybeAccountRecord.isPresent()) {
                        AccountRecord ar = maybeAccountRecord.get();
                        CompletableFuture<Optional<UserRecord>> userById = userDAO.getUserById(ar.getUserId());
                         return userById.thenComposeAsync(maybeUser-> {
                            if(maybeUser.isPresent()){
                                UserRecord user = maybeUser.get();
                                return  CompletableFuture.supplyAsync(()->Optional.of(getAccountFromRecords( ar,  user)));
                            }else{
                                return CompletableFuture.supplyAsync(()->Optional.empty());
                            }
                        });

                    }
                    else {
                        return CompletableFuture.supplyAsync(() -> Optional.empty());
                    }
                });
    }

    @Override
    public CompletableFuture<Optional<Account>> getAccountByUserId(String userId) {
        return accountDAO.getAccountByUserId(userId)
                .thenComposeAsync((maybeAccountRecord) -> {
                    if (maybeAccountRecord.isPresent()) {
                        AccountRecord ar = maybeAccountRecord.get();
                        CompletableFuture<Optional<UserRecord>> userById = userDAO.getUserById(ar.getUserId());
                        return userById.thenComposeAsync(maybeUser-> {
                            if(maybeUser.isPresent()){
                                UserRecord user = maybeUser.get();
                                return  CompletableFuture.supplyAsync(()->Optional.of(getAccountFromRecords( ar,  user)));
                            }else{
                                return CompletableFuture.supplyAsync(()->Optional.empty());
                            }
                        });

                    }
                    else {
                        return CompletableFuture.supplyAsync(() -> Optional.empty());
                    }
                });
    }


    @Override
    public CompletableFuture<Account> updateAccountBalance(Account account) {

            return accountDAO.updateBalance(account.getAccountNumber(), account.getBalance())
                    .thenCompose(r-> CompletableFuture.supplyAsync(()->account));

    }

    @Override
    public CompletableFuture<Account> deleteAccount(Account account) {
        return  accountDAO.deleteAccount(account.getAccountNumber())
                .thenCompose(ad->userDAO.deleteUser(account.getUser().getId()))
                .thenCompose(r-> CompletableFuture.supplyAsync(()->account));
    }

    private Account getAccountFromRecords(AccountRecord ar, UserRecord ur){
        return new Account(ar.getId(),
                new User(ur.getId(),ur.getName(),ur.getLastName(),
                        EDocumentType.valueOf(ur.getDocumentType()),
                        ur.getDocumentId(),ur.getEmail(),ur.getBirthDay(),
                        EGender.valueOf(ur.getGender())),ar.getBalance(),
                ECurrency.valueOf(ar.getCurrency()));
    }
}
