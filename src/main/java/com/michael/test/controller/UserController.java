package com.michael.test.controller;

import com.michael.test.model.Users;
import com.michael.test.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/user/{id}")
    public ResponseEntity<Object> findByUsername(@PathVariable String id) {
        Users user = userRepository.findById(id).orElse(null);

        return ResponseEntity.ok(user);
    }
}
