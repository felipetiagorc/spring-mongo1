package com.felipe.springmongo1.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.felipe.springmongo1.domain.Post;
import com.felipe.springmongo1.domain.User;
import com.felipe.springmongo1.dto.AuthorDTO;
import com.felipe.springmongo1.repository.PostRepository;
import com.felipe.springmongo1.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		userRepository.deleteAll(); //limpa o banco primeiro
		postRepository.deleteAll();
		
		
		//simpledateFormat pra data:
		SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		
		User maria = new User(null, "Maria das Dores", "mdores@hg.com");
		User jose = new User(null, "Jose Silva", "jose@yaho.com");
		User bob = new User(null, "Bob Marli", "bob@marli.com");
		
		userRepository.saveAll(Arrays.asList(maria, jose, bob));
		
		
		// instanciando Posts, já associado com o autor deles.. ultimo param: 
		Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu viagem", "Vou viajar pra SP abraços", new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("23/04/2018"), "Bom dia", "Acordei feliz hoje", new AuthorDTO(maria));
		

		postRepository.saveAll(Arrays.asList(post1, post2));
		
		
		maria.getPosts().addAll(Arrays.asList(post1, post2));
		userRepository.save(maria);
	}

}
