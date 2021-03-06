package com.felipe.springmongo1.services;


import java.util.Date;
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
	
//	// Com Query Methods | Aqui pode ser o nome do método que quiser.. 
//	public List<Post> findByTitle(String text){
//		return repo.findByTitleContainingIgnoreCase(text);
//	}
//	
	
	// Com @Query
	// aqui pode ser o nome do método que quiser..2
	public List<Post> findByTitle(String text){
		return repo.pesquisaTitulo(text);
	}
	
	public List<Post> fullSearch(String text, Date min, Date max){
		// acrescentando um dia para buscar até a meia noite do ultima dia
		max = new Date(max.getTime() + 24 * 60 * 60 * 1000);
		return repo.fullSearch(text, min, max);
		
	}
	
	
}
