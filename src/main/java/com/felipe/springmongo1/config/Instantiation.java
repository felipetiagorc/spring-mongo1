package com.felipe.springmongo1.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.felipe.springmongo1.domain.User;
import com.felipe.springmongo1.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void run(String... args) throws Exception {
		userRepository.deleteAll(); //limpa o banco primeiro
		
		User maria = new User(null, "Maria das Dores", "mdores@hg.com");
		User jose = new User(null, "Jose Silva", "jose@yaho.com");
		User bob = new User(null, "Bob Marli", "bob@marli.com");
		
		userRepository.saveAll(Arrays.asList(maria, jose, bob));
	}

}
