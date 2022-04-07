package com.rafael.storeapi.service;

import com.rafael.storeapi.dto.UserDTO;
import com.rafael.storeapi.exception.InvalidValueFieldException;
import com.rafael.storeapi.model.Authority;
import com.rafael.storeapi.model.User;
import com.rafael.storeapi.repository.AuthorityRepository;
import com.rafael.storeapi.repository.UserRepository;
import com.rafael.storeapi.repository.specification.UserSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;

    public Page<UserDTO> listUsers(List<String> userNameList, Pageable pageable) {
        if (userNameList == null) return userRepository.findAll(pageable).map(UserDTO::convert);
        return userRepository
                .findAll(UserSpecification.filterByUserNameList(userNameList), pageable)
                .map(UserDTO::convert);
    }

    public UserDTO createUser(UserDTO userDTO) {
        if (userDTO.getUserName().equals("adminMaster")) throw new ResponseStatusException(HttpStatus.FORBIDDEN, "userName cannot be adminMaster");
        User userDB = this.registerUser(userDTO);
        List<Authority> authorities = new ArrayList<>();
        userDTO.getRoles()
                .forEach(a -> authorities.add(saveAuthority(userDB, a)));
        userDB.setAuthorities(authorities);
        return UserDTO.convert(userDB);
    }

    public List<UserDTO> deleteUsers(List<String> userNameList) {
        userNameList.removeAll(List.of("adminMaster"));
        List<User> userDBList = userRepository.findAllById(userNameList);
        userRepository.deleteAll(userDBList);
        return userDBList.stream().map(UserDTO::convert).toList();
    }

    public UserDTO createClient(UserDTO userDTO) {
        if (userDTO.getUserName().equals("adminMaster")) throw new ResponseStatusException(HttpStatus.FORBIDDEN, "userName cannot be adminMaster");
        User userDB = this.registerUser(userDTO);
        Authority authority = saveAuthority(userDB, "CLIENT");
        userDB.setAuthorities(List.of(authority));
        return UserDTO.convert(userDB);
    }

    public List<UserDTO> deleteClients(Principal principal) {
        List<String> userNameList = new ArrayList<>();
        userNameList.add(principal.getName());
        return deleteUsers(userNameList);
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

    private Authority saveAuthority(User user, String role) {
        Authority authority = Authority.convert(user, role);
        return authorityRepository.save(authority);
    }
}
