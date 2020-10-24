 package com.felipe.springmongo1.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
	
	//  endpoint para buscar usuario por id:
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<UserDTO> findById(@PathVariable String id) {
		// instancia obj usuario recebendo service.findById(id):
		User obj = service.findById(id);
		
		return ResponseEntity.ok().body(new UserDTO(obj)); 
		
	}
	
	//  endpoint para inserir usuario:
		@RequestMapping(method=RequestMethod.POST)
		// também podia ser apenas @PostMapping no lugar da linha de cima
		
		//aqui o método vai retornar um obj vazio: Void | @Request.. Insere um objDTO do tipo UserDTO
	public ResponseEntity<Void> insert(@RequestBody UserDTO objDto) {
		// instancia obj usuario recebendo service.fromDTO, passando o objeto do body
			// com isso, converteu o objDto para 'User obj'
		User obj = service.fromDTO(objDto);
		// agora chama o insert:
		obj = service.insert(obj);
		// Esse método retorna resposta vazia, porém, vamos coloar o cabeçalho do novo recurso criado.(boa prática)
		// Recurso do Spring = instancia um obj do tipo URI. Faz essa chamada enorme passando '(obj.getId()).toUri()' como argumento
		// que vai pegar o endereço do novo objeto inserido.
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		//retorna responsta vazia, com o codigo 201, contendo a localizacao no do novo recurso criado:
		return ResponseEntity.created(uri).build();
		
	}
	

} 
