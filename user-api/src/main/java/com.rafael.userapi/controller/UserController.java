package com.rafael.userapi.controller;

import com.rafael.userapi.dto.UserDTO;
import com.rafael.userapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @GetMapping
    public Principal getCurrentUser(Principal principal) {
        return principal;
    }

    @GetMapping("/admin")
    public Page<UserDTO> listUsers(@RequestBody(required = false) List<String> userNameList, Pageable pageable) { return userService.listUsers(userNameList, pageable); }

    @PostMapping("/admin")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO saveUser(@Valid @RequestBody UserDTO userDTO) { return userService.createUser(userDTO); }

    @DeleteMapping("/admin")
    public List<UserDTO> deleteUsers(@RequestBody List<String> userNameList) { return userService.deleteUsers(userNameList); }

    @PostMapping("/sign_in")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO saveClient(@Valid @RequestBody UserDTO userDTO) { return userService.createClient(userDTO); }

    @DeleteMapping("/sign_out")
    public List<UserDTO> deleteClients(Principal principal) { return userService.deleteClients(principal); }
}