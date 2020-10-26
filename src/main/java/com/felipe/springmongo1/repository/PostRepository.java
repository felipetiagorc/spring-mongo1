package com.felipe.springmongo1.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.felipe.springmongo1.domain.Post;
import com.felipe.springmongo1.domain.User;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

	Post save(Optional<Post> newObj);

}
