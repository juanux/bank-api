package com.mybank.co.dao;

import com.mybank.co.dao.tables.records.UserRecord;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public interface IUserDAO {

     CompletableFuture<Integer> createUser(UserRecord userRecord);
     CompletableFuture<Integer> updateUser(UserRecord userRecord);
     CompletionStage<Integer> deleteUser(UUID userId);
     CompletableFuture<Optional<UserRecord>> getUserById(UUID userId);
}
