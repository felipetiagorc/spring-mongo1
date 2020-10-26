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
	
	/*
	Ainda será preciso realizar mais uma correção no código para o Spring 2.x.x. 
	Pois na verdade repo.findByid(obj.getId()) irá retornar um 'Optional<User>' 
	e não um 'User'. Para extrair o 'User' do 'Optional<User>' chame a função 
	'.get()' do próprio 'Optional<>'. 
	*/
	
	
	public User update(User obj) {
	User newObj = repo.findById(obj.getId()).get();
		//copia os dados novos 
		updateData(newObj, obj); // clicar no updData e criar método:
		return repo.save(newObj);
		
		
		
	}
	
	private void updateData(User newObj, User obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
		
		
	}

	// implementando o fromDTO:
	public User fromDTO(UserDTO objDto) {
		return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
	}
}
