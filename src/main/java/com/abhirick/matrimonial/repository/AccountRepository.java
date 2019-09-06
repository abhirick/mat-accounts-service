package com.abhirick.matrimonial.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.abhirick.matrimonial.model.Account;

public interface AccountRepository extends MongoRepository<Account, String>, AccountCustomRepository {

}
