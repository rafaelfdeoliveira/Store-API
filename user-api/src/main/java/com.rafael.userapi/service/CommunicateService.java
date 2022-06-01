package com.rafael.userapi.service;

import com.rafael.userapi.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@FeignClient("eureka-client-users")
public interface CommunicateService {

    @GetMapping
    Principal getCurrentUser(Principal principal);

    @GetMapping("/admin")
    Page<UserDTO> listUsers(@RequestBody(required = false) List<String> userNameList, Pageable pageable);

    @PostMapping("/admin")
    @ResponseStatus(HttpStatus.CREATED)
    UserDTO saveUser(@Valid @RequestBody UserDTO userDTO);

    @DeleteMapping("/admin")
    List<UserDTO> deleteUsers(@RequestBody List<String> userNameList);

    @PostMapping("/sign_in")
    @ResponseStatus(HttpStatus.CREATED)
    UserDTO saveClient(@Valid @RequestBody UserDTO userDTO);

    @DeleteMapping("/sign_out")
    List<UserDTO> deleteClients(Principal principal);
}
