package com.felipe.springmongo1.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.felipe.springmongo1.domain.User;
import com.felipe.springmongo1.dto.UserDTO;
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
	// implementando método insert:
	// como o repositório retorna um Objeto, foi mantido esse padrão também no serviço "User"
	public User insert(User obj) {
		return repo.insert(obj);
	}
	
	//Delete
	
	public void delete(String id) {
		//pra aproveitar a excecção do findById, vamos fazer a busca primeiro, se não encontrar da o erro ja impl.:
		findById(id);
		repo.deleteById(id);
	}
	
	
	//parece q esse fromDTO não ta aqui nessa classe, no dele..
	
	// implementando o fromDTO:
	public User fromDTO(UserDTO objDto) {
		return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
	}
}
