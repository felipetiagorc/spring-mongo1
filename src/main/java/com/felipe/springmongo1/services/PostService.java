package com.felipe.springmongo1.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.felipe.springmongo1.domain.Post;
import com.felipe.springmongo1.repository.PostRepository;
import com.felipe.springmongo1.services.exception.ObjectNotFoundException;

@Service
public class PostService {
	
	@Autowired
	private PostRepository repo;

	public Post findById(String id) {
		Optional<Post> user = repo.findById(id);
		// se não existir usuário com o id passado acima, vai retornar nulo então precisa tratar:
		return user.orElseThrow(() -> new ObjectNotFoundException("Objeto nao encontrado"));
		}
	
	// aqui pode ser o nome do método que quiser..
	public List<Post> findByTitle(String text){
		return repo.findByTitleContainingIgnoreCase(text);
	}
}
