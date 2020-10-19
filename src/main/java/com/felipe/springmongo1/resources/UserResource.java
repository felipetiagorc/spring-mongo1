 package com.felipe.springmongo1.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.felipe.springmongo1.domain.User;
import com.felipe.springmongo1.dto.UserDTO;
import com.felipe.springmongo1.services.UserService;

@RestController 
@RequestMapping(value="/users") 

public class UserResource {
	@Autowired
	private UserService service;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<UserDTO>> findAll() {
		// carrega a lista de usuários (ok)
		// depois tem que converter essa lista de User para uma lista de UserDTO:
		List<User> list = service.findAll();
		// cria uma listDto que vai receber um stream da list e mapeia cada item x (lambda)  
		// depois tem que transformar o resultado do map de volta em uma lista 
		// com '.collect(Collector.toList())':
		List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		// retorna o listDto:
		return ResponseEntity.ok().body(listDto); 
		
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<UserDTO> findById(@PathVariable String id) {
		// instancia obj usuario recebendo service.findById(id):
		User obj = service.findById(id);
		
		return ResponseEntity.ok().body(new UserDTO(obj)); 
		
	}
	

}
