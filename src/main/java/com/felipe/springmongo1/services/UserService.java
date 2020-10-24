package com.felipe.springmongo1.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.felipe.springmongo1.domain.User;
import com.felipe.springmongo1.repository.UserRepository;
import com.felipe.springmongo1.services.exception.ObjectNotFoundException;

@Service
public class UserService {
	@Autowired
	private UserRepository repo;

	public List<User> findAll() {
		return repo.findAll();
	}

	public User findById(String id) {
		Optional<User> user = repo.findById(id);
		// se não existir usuário com o id passado acima, vai retornar nulo então precisa tratar:
		if(user == null) {
			throw new ObjectNotFoundException("Objeto não encontrado!");
			
		}
		return user.orElseThrow(() -> new ObjectNotFoundException("Objeto nao encontrado"));

	}

}
