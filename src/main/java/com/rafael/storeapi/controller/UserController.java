package com.rafael.storeapi.controller;

import com.rafael.storeapi.dto.UserDTO;
import com.rafael.storeapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @GetMapping
    public Principal getCurrentUser(Principal principal) {
        return principal;
    }

    @PostMapping("/admin")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO saveUser(@Valid @RequestBody UserDTO userDTO) { return userService.createUser(userDTO); }

    @DeleteMapping("/admin")
    public UserDTO deleteUser(@RequestBody String userName) { return userService.deleteUser(userName); }

    @PostMapping("/registration")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO saveClient(@Valid @RequestBody UserDTO userDTO) { return userService.createClient(userDTO); }

    @DeleteMapping("/registration")
    public UserDTO deleteClient(Principal principal) { return userService.deleteClient(principal); }
}