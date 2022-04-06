package com.rafael.storeapi;

import com.rafael.storeapi.model.Authority;
import com.rafael.storeapi.model.AuthorityKey;
import com.rafael.storeapi.model.User;
import com.rafael.storeapi.repository.AuthorityRepository;
import com.rafael.storeapi.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class StoreApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(StoreApiApplication.class, args);
	}

	@Bean
	public CommandLineRunner test(UserRepository userRepository,
								  AuthorityRepository authorityRepository,
								  PasswordEncoder encoder) {
		return (args) -> {
			User user = new User();
			user.setUserName("rafael");
			user.setPassword(encoder.encode("123"));
			user.setEnabled(true);
			userRepository.save(user);

			Authority authority = new Authority();
			authority.setAuthorityKey(new AuthorityKey(user.getUserName(), "ADMIN"));
			authority.setUser(user);
			authorityRepository.save(authority);

			authority = new Authority();
			authority.setAuthorityKey(new AuthorityKey(user.getUserName(), "CLIENT"));
			authority.setUser(user);
			authorityRepository.save(authority);
		};
	}
}
