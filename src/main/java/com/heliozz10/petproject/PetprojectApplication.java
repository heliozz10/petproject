package com.heliozz10.petproject;

import com.heliozz10.petproject.entity.Authority;
import com.heliozz10.petproject.entity.User;
import com.heliozz10.petproject.repository.AuthorityRepository;
import com.heliozz10.petproject.repository.UserRepository;
import com.heliozz10.petproject.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.autoconfigure.openai.OpenAiAutoConfiguration;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.function.context.config.ContextFunctionCatalogAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class PetprojectApplication extends SpringBootServletInitializer {
	private static final Logger LOGGER = LoggerFactory.getLogger(PetprojectApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(PetprojectApplication.class, args);
	}

	@Bean
	public CommandLineRunner setup(AuthorityRepository authorityRepository, UserRepository userRepository, UserService userService) {
		return args -> {
			//Create the USER authority if not present
			authorityRepository.findByName("USER").ifPresentOrElse(user -> {}, () -> authorityRepository.save(new Authority("USER")));
			//TODO: REMOVE IN PRODUCTION
			userRepository.deleteAll();
		};
	}
}
