package com.rafael.userapi.controller;

import com.rafael.userapi.dto.UserDTO;
import com.rafael.userapi.service.CommunicateService;
import com.rafael.userapi.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController implements CommunicateService {
    private final UserService userService;

    @GetMapping
    public Principal getCurrentUser(Principal principal) {
        return principal;
    }

    @Retry(name = "listUsers", fallbackMethod = "fallback")
    @CircuitBreaker(name = "listUsers", fallbackMethod = "fallback")
    @RateLimiter(name = "listUsers", fallbackMethod = "fallback")
    @GetMapping("/admin")
    public Page<UserDTO> listUsers(@RequestBody(required = false) List<String> userNameList, Pageable pageable) { return userService.listUsers(userNameList, pageable); }

    @Retry(name = "saveUser", fallbackMethod = "fallback")
    @CircuitBreaker(name = "saveUser", fallbackMethod = "fallback")
    @RateLimiter(name = "saveUser", fallbackMethod = "fallback")
    @PostMapping("/admin")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO saveUser(@Valid @RequestBody UserDTO userDTO) { return userService.createUser(userDTO); }

    @Retry(name = "deleteUsers", fallbackMethod = "fallback")
    @CircuitBreaker(name = "deleteUsers", fallbackMethod = "fallback")
    @RateLimiter(name = "deleteUsers", fallbackMethod = "fallback")
    @DeleteMapping("/admin")
    public List<UserDTO> deleteUsers(@RequestBody List<String> userNameList) { return userService.deleteUsers(userNameList); }

    @Retry(name = "saveClient", fallbackMethod = "fallback")
    @CircuitBreaker(name = "saveClient", fallbackMethod = "fallback")
    @RateLimiter(name = "saveClient", fallbackMethod = "fallback")
    @PostMapping("/sign_in")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO saveClient(@Valid @RequestBody UserDTO userDTO) { return userService.createClient(userDTO); }

    @Retry(name = "deleteClients", fallbackMethod = "fallback")
    @CircuitBreaker(name = "deleteClients", fallbackMethod = "fallback")
    @RateLimiter(name = "deleteClients", fallbackMethod = "fallback")
    @DeleteMapping("/sign_out")
    public List<UserDTO> deleteClients(Principal principal) { return userService.deleteClients(principal); }

    public Page<UserDTO> fallback(@RequestBody(required = false) List<String> userNameList, Pageable pageable, Exception e) {
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public UserDTO fallback(@Valid @RequestBody UserDTO userDTO, Exception e) {
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public List<UserDTO> fallback(@RequestBody List<String> userNameList, Exception e) {
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public List<UserDTO> fallback(Principal principal) {
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}