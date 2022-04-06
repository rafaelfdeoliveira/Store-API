package com.rafael.storeapi.service;

import com.rafael.storeapi.dto.UserDTO;
import com.rafael.storeapi.exception.InvalidValueFieldException;
import com.rafael.storeapi.model.Authority;
import com.rafael.storeapi.model.User;
import com.rafael.storeapi.repository.AuthorityRepository;
import com.rafael.storeapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;

    public UserDTO createUser(UserDTO userDTO) {
        User userDB = this.registerUser(userDTO);
        userDTO.getRoles()
                .forEach(a -> saveAuthority(userDB, a));
        User registeredUser = userRepository.findById(userDB.getUserName()).orElseThrow(() -> new RuntimeException("User Registration failed"));
        return UserDTO.convert(registeredUser);
    }

    public UserDTO deleteUser(String userName) {
        User userDB = userRepository.findById(userName).orElseThrow(() -> new RuntimeException("User " + userName + " not found"));
        userRepository.delete(userDB);
        return UserDTO.convert(userDB);
    }

    public UserDTO createClient(UserDTO userDTO) {
        User userDB = this.registerUser(userDTO);
        saveAuthority(userDB, "CLIENT");
        User registeredUser = userRepository.findById(userDB.getUserName()).orElseThrow(() -> new RuntimeException("Client Registration failed"));
        return UserDTO.convert(registeredUser);
    }

    public UserDTO deleteClient(Principal principal) {
        return deleteUser(principal.getName());
    }

    private User registerUser(UserDTO userDTO) {
        if (userDTO.getEnabled() == null) userDTO.setEnabled(true);
        if (userDTO.getPassword() == null) {
            throw new InvalidValueFieldException("A password must be provided");
        }

        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        User user = User.convert(userDTO);

        return userRepository.save(user);
    }

    private void saveAuthority(User user, String role) {
        Authority authority = Authority.convert(user, role);
        authorityRepository.save(authority);
    }
}
