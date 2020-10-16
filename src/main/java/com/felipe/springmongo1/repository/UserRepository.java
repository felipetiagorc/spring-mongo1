package com.felipe.springmongo1.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.felipe.springmongo1.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

}
