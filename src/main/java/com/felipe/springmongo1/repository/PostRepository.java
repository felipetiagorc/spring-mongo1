package com.felipe.springmongo1.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.felipe.springmongo1.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

	Post save(Optional<Post> newObj);
	
	List<Post> findByTitleContainingIgnoreCase(String text);
	
	@Query("{ 'title': { $regex: ?0, $options: 'i'} }")
	List<Post> pesquisaTitulo(String text);
	

}
