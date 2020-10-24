package com.felipe.springmongo1.dto;

import java.io.Serializable;

import com.felipe.springmongo1.domain.User;

public class UserDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String email;
	
	public UserDTO() {}
	
	//construtor para instanciar A PARTIR do obj Entity correspondente: (no caso User)
	// o construtor pega um Usuário e instancia o DTO pra gente:
	public UserDTO(User obj) {
		id = obj.getId();
		name = obj.getName();
		email = obj.getEmail();
	}

	public String getId() {
		return id; 
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
