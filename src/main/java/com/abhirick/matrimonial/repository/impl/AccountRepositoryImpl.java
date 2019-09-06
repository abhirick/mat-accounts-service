package com.abhirick.matrimonial.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.abhirick.matrimonial.repository.AccountCustomRepository;

@Repository
public class AccountRepositoryImpl implements AccountCustomRepository {

	@Autowired
	MongoTemplate mongoTemplate;

}
