package com.michael.test.services;

import com.michael.test.controller.dto.request.LoginRequest;
import com.michael.test.controller.dto.request.SignupRequest;
import com.michael.test.model.Authentications;
import com.michael.test.model.Users;
import com.michael.test.repository.AuthenticationRepository;
import com.michael.test.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationService {

    private final AuthenticationProvider authenticationManager;
    private final AuthenticationRepository authenticationRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public Authentications authenticate(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        return authenticationRepository.findByUsername(request.getUsername()).orElseThrow(null);
    }

    public Users signup(SignupRequest signupRequest) {
        try {
            Users user = new Users(signupRequest);
            Users userSave = userRepository.save(user);

            String passwordEncode = passwordEncoder.encode(signupRequest.getPassword());

            Authentications authUser = new Authentications(user, signupRequest, passwordEncode);
            authenticationRepository.save(authUser);

            return userSave;
        } catch (Exception e) {
            return null;
        }
    }
}
