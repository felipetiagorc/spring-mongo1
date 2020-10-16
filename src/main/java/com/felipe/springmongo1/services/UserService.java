package com.felipe.springmongo1.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.felipe.springmongo1.domain.User;
import com.felipe.springmongo1.repository.UserRepository;

@Service
public class UserService { 
	@Autowired	
	private UserRepository repo;
	
	public List<User> findAll() {
		return repo.findAll();
		
		
	}

}
 