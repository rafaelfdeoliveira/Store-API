package com.rafael.userapi;

import com.rafael.userapi.model.Authority;
import com.rafael.userapi.model.AuthorityKey;
import com.rafael.userapi.model.User;
import com.rafael.userapi.repository.AuthorityRepository;
import com.rafael.userapi.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class UserApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApiApplication.class, args);
    }

    @Bean
    public CommandLineRunner test(UserRepository userRepository,
                                  AuthorityRepository authorityRepository,
                                  PasswordEncoder encoder) {
        return (args) -> {
            User user = new User();
            user.setUserName("adminMaster");
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