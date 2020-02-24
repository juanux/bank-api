package com.mybank.co.dao;


import com.mybank.co.dao.jooq.tables.records.UserRecord;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

/**
 * User Data Access Operations for users
 */
public interface IUserDAO {

     /**
      * Create a new user
      * @param userRecord
      * @return
      */
     CompletableFuture<Integer> createUser(UserRecord userRecord);

     /**
      * Update a user
      * @param userRecord
      * @return
      */
     CompletableFuture<Integer> updateUser(UserRecord userRecord);

     /**
      * Delete an user
      * @param userId
      * @return
      */
     CompletionStage<Integer> deleteUser(String userId);

     /**
      * Get an user for the given id
      * @param userId
      * @return
      */
     CompletableFuture<Optional<UserRecord>> getUserById(String userId);
}
